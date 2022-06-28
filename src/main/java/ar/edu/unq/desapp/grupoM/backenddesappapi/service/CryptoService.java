package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.DatabaseInitializate;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.BinanceAPI;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    Logger log = LoggerFactory.getLogger(DatabaseInitializate.class);

    BinanceAPI binanceAPI = new BinanceAPI();

    public Crypto createCrypto(String symbol, Double price) {

        Crypto newCrypto = new Crypto(symbol, price, new Date());
        return cryptoRepository.save(newCrypto);
    }

    public List<Crypto> findBySymbolIs(String symbol) {
     return  cryptoRepository.findBySymbolIs(symbol);
    }

    public Crypto findCrypto(String symbol){
        Crypto dataBaseCrypto = null;
        Crypto binanceCrypto = null;
        try{
            dataBaseCrypto = getCryptoFromDatabase(symbol);}catch(Exception e) {
        }

        if (dataBaseCrypto != null){
            if(LastCrpytoUpdate2MinutesAgo(symbol)) {
                binanceCrypto = callBinanceAPI(symbol);
                Crypto updateCrypto = this.updateCrypto(binanceCrypto.symbol, binanceCrypto.price);
                return updateCrypto;
            }
            else {
                return dataBaseCrypto;
            }
        }else {
            binanceCrypto = callBinanceAPI(symbol);
            Crypto newCrypto = this.createCrypto(binanceCrypto.symbol, binanceCrypto.price);
            return newCrypto;
        }
    }

    //@Scheduled(cron = "0 0/10 * * * *") // this is a cron that runs every 10 minutes
    public List<Crypto> getCryptos(){
        log.info("Crypto price cron started");
        List<Crypto> cryptoList = new ArrayList<Crypto>();
        for (Crypto.Cryptos crypto_enum : Crypto.Cryptos.values()) {
            Crypto dataBaseCrypto = null;
            Crypto binanceCrypto = null;

            try{dataBaseCrypto = this.findBySymbolIs(crypto_enum.toString()).stream().findFirst().get();}catch(Exception e) {}

            if (dataBaseCrypto != null){
                if(LastCrpytoUpdate2MinutesAgo(crypto_enum.toString())) {
                    binanceCrypto = callBinanceAPI(crypto_enum.toString());
                    Crypto updateCrypto = this.updateCrypto(binanceCrypto.symbol, binanceCrypto.price);
                    cryptoList.add(updateCrypto);
                }
                else {
                    cryptoList.add(dataBaseCrypto);
                }
            }else {
                binanceCrypto = callBinanceAPI(crypto_enum.toString());
                Crypto newCrypto = this.createCrypto(binanceCrypto.symbol, binanceCrypto.price);
                cryptoList.add(newCrypto);
            }
        }
        log.info("Crypto price cron finished");
        return cryptoList;
    }

    public Crypto updateCrypto(String symbol, Double price) {
        Crypto cryptoToModify = cryptoRepository.findBySymbolIs(symbol).stream().findFirst().get();

        cryptoToModify.price = price;
        cryptoToModify.price_date = new Date();

        return cryptoRepository.save(cryptoToModify);
    }

    // Auxiliar methods

    public Crypto callBinanceAPI(String symbol){
        Crypto crypto = binanceAPI.call(symbol);
        log.info("Updating " + symbol + " price from Binance api");
        return crypto;
    }

    public Boolean LastCrpytoUpdate2MinutesAgo(String symbol){
        Crypto crypto = getCryptoFromDatabase(symbol);
        Date date_now = new Date();
        long duration  =   date_now.getTime() - crypto.getPrice_date().getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        log.info(crypto.getName() + " last price update was " + diffInMinutes + " minutes ago");
        return diffInMinutes >= 2;
    }

    public Crypto getCryptoFromDatabase(String symbol){
        return this.findBySymbolIs(symbol).stream().findFirst().get();
    }
}

