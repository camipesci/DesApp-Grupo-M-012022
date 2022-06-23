package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.BinanceAPI;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Date;
import java.util.List;

@Service
public class CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    BinanceAPI binanceAPI = new BinanceAPI();

    RestTemplate restTemplate = new RestTemplate();

    public Crypto createCrypto(String symbol, Double price) {

        Crypto newCrypto = new Crypto(symbol, price, new Date());
        return cryptoRepository.save(newCrypto);
    }

    public List<Crypto> findBySymbolIs(String symbol) {
     return  cryptoRepository.findBySymbolIs(symbol);
    }

    public List<Crypto> findCryptoOrCallBinanceAPI(String symbol) {
        // Cryptos from database will have the latest from the cron
        List<Crypto> listCrypto = cryptoRepository.findBySymbolIs(symbol);
        if(listCrypto.size() == 0){
            Crypto crypto = binanceAPI.call(symbol);
            crypto.setPrice_date(new Date());
            listCrypto.add(crypto);
        }else{
            return listCrypto;
        }

        return listCrypto;
    }

    public Crypto updateCrypto(String symbol, Double price) {
        Crypto cryptoToModify = cryptoRepository.findBySymbolIs(symbol).stream().findFirst().get();

        cryptoToModify.price = price;
        cryptoToModify.price_date = new Date();

        return cryptoRepository.save(cryptoToModify);
    }
}

