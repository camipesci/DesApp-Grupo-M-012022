package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.DatabaseInitializate;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CryptoService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Api(tags = "Crypto Controller")
@Tag(name = "Crypto Controller", description = "Manage Crypto ABM")
@RestController
public class CryptoController {

    RestTemplate restTemplate = new RestTemplate();

    Logger log = LoggerFactory.getLogger(DatabaseInitializate.class);

    BinanceAPI binanceAPI = new BinanceAPI();
    @Autowired
    private CryptoService cryptoService;

    @Operation(summary = "Get and persist a crypto by its name")
    @GetMapping("/api/cryptos/{symbol}")
    @Transactional
    public ResponseEntity<CryptoDTO> getCryptoPrice(@PathVariable String symbol) {
        Crypto dataBaseCrypto = null;
        Crypto binanceCrypto = null;
        try{
            dataBaseCrypto = getCryptoFromDatabase(symbol);}catch(Exception e) {
        }

        if (dataBaseCrypto != null){
            if(LastCrpytoUpdate2MinutesAgo(symbol)) {
                binanceCrypto = callBinanceAPI(symbol);
                Crypto updateCrypto = cryptoService.updateCrypto(binanceCrypto.symbol, binanceCrypto.price);
                return ResponseEntity.ok().body(CryptoDTO.from(updateCrypto));
            }
            else {
                return ResponseEntity.ok().body(CryptoDTO.from(dataBaseCrypto));
            }
        }else {
            binanceCrypto = callBinanceAPI(symbol);
            Crypto newCrypto = cryptoService.createCrypto(binanceCrypto.symbol, binanceCrypto.price);
            return ResponseEntity.ok().body(CryptoDTO.from(newCrypto));
        }

    }

    @Operation(summary = "Gets and persists all cryptos related to the project scope")
    @GetMapping("/api/cryptos")
    @Transactional
    public ResponseEntity<List<CryptoDTO>> getAllCryptosPrice() {
        List<Crypto> cryptoList = new ArrayList<Crypto>();
        for (Crypto.Cryptos crypto_enum : Crypto.Cryptos.values()) {
            Crypto dataBaseCrypto = null;
            Crypto binanceCrypto = null;

            try{dataBaseCrypto = cryptoService.findBySymbolIs(crypto_enum.toString()).stream().findFirst().get();}catch(Exception e) {}

            if (dataBaseCrypto != null){
                if(LastCrpytoUpdate2MinutesAgo(crypto_enum.toString())) {
                    binanceCrypto = callBinanceAPI(crypto_enum.toString());
                    Crypto updateCrypto = cryptoService.updateCrypto(binanceCrypto.symbol, binanceCrypto.price);
                    cryptoList.add(updateCrypto);
                }
                else {
                    cryptoList.add(dataBaseCrypto);
                }
            }else {
                binanceCrypto = callBinanceAPI(crypto_enum.toString());
                Crypto newCrypto = cryptoService.createCrypto(binanceCrypto.symbol, binanceCrypto.price);
                cryptoList.add(newCrypto);
            }
        }
        return ResponseEntity.ok().body(CryptoDTO.from(cryptoList));
    }

    // Exception handle

    @ExceptionHandler(HttpClientErrorException.class)
    // This is the exception raised by binance when passing an invalid crypto name
    public ResponseEntity handleException(HttpClientErrorException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Crypto not found");
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
        return cryptoService.findBySymbolIs(symbol).stream().findFirst().get();
    }
}
