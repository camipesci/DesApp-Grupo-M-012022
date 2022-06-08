package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;


@RestController
public class BinanceController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/api/cryptos/{symbol}")
    public ResponseEntity<CryptoDTO> getCryptoPrice(@PathVariable String symbol) {
        CryptoCurrency crypto = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, CryptoCurrency.class);
        CryptoCurrency dataBaseCrypto = null;
        try{
            dataBaseCrypto = currencyService.findBySymbolIs(symbol).stream().findFirst().get();}catch(Exception e) {

        }

        if (dataBaseCrypto != null && crypto.symbol.equals(dataBaseCrypto.symbol)){
            CryptoCurrency updateCrypto = currencyService.updateCrypto(crypto.symbol,crypto.price);
            return ResponseEntity.ok().body(CryptoDTO.from(updateCrypto));
        }else {
            CryptoCurrency newCrypto = currencyService.createCrypto(crypto.symbol, crypto.price);
            return ResponseEntity.ok().body(CryptoDTO.from(newCrypto));
        }

    }

    @GetMapping("/api/cryptos")
    public ResponseEntity<List<CryptoDTO>> getAllCryptosPrice() {
        List<CryptoCurrency> cryptoCurrencyList = new ArrayList<CryptoCurrency>();
        for (CryptoCurrency.Cryptos crypto_enum :CryptoCurrency.Cryptos.values()) {
            CryptoCurrency crypto = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + crypto_enum, CryptoCurrency.class);

            CryptoCurrency dataBaseCrypto = null;
            try{
                dataBaseCrypto = currencyService.findBySymbolIs(crypto.symbol).stream().findFirst().get();}catch(Exception e) {
                //  Block of code to handle errors
            }

            if (dataBaseCrypto != null && crypto.symbol.equals(dataBaseCrypto.symbol)){
                CryptoCurrency updateCrypto = currencyService.updateCrypto(crypto.symbol,crypto.price);
                cryptoCurrencyList.add(updateCrypto);
            }else {
                CryptoCurrency newCrypto = currencyService.createCrypto(crypto.symbol, crypto.price);
                cryptoCurrencyList.add(newCrypto);
            }
        }
        return ResponseEntity.ok().body(CryptoDTO.from(cryptoCurrencyList));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    // This is the exception raised by binance when passing an invalid crypto name
    public ResponseEntity handleException(HttpClientErrorException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Crypto not found");
    }

    @GetMapping("/api/cryptos_test/{symbol}")
    public ResponseEntity<CryptoDTO> cryptoTest(@PathVariable String symbol) {
        CryptoCurrency crypto = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, CryptoCurrency.class);
        CryptoCurrency dataBaseCrypto = null;
        try{
            dataBaseCrypto = currencyService.findBySymbolIs(symbol).stream().findFirst().get();}catch(Exception e) {
            //  Block of code to handle errors
        }

        return ResponseEntity.ok().body(CryptoDTO.from(dataBaseCrypto));

    }
}
