package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class BinanceController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api/cryptos/{symbol}")
    public ResponseEntity<CryptoDTO> getCryptoPrice(@PathVariable String symbol) {
        CryptoCurrency crypto = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, CryptoCurrency.class);
               return ResponseEntity.ok().body(CryptoDTO.from(crypto));
    }

    @GetMapping("/api/cryptos")
    public ResponseEntity<List<CryptoDTO>> getAllCryptosPrice() {
        List<CryptoCurrency> cryptoCurrencyList = new ArrayList<CryptoCurrency>();
        for (CryptoCurrency.Cryptos crypto_enum :CryptoCurrency.Cryptos.values()) {
            CryptoCurrency crypto = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + crypto_enum, CryptoCurrency.class);
            cryptoCurrencyList.add(crypto);
        }
        return ResponseEntity.ok().body(CryptoDTO.from(cryptoCurrencyList));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleException(HttpClientErrorException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Crypto not found");
    }
}
