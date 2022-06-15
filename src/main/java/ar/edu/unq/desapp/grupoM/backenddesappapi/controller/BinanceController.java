package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CryptoService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

@Api(tags = "Crypto Controller")
@Tag(name = "Crypto Controller", description = "Manage Crypto ABM")
@RestController
public class BinanceController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CryptoService cryptoService;

    @Operation(summary = "Get and persist a crypto by its name")
    @GetMapping("/api/cryptos/{symbol}")
    public ResponseEntity<CryptoDTO> getCryptoPrice(@PathVariable String symbol) {
        CryptoCurrency crypto = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, CryptoCurrency.class);
        CryptoCurrency dataBaseCrypto = null;
        try{
            dataBaseCrypto = cryptoService.findBySymbolIs(symbol).stream().findFirst().get();}catch(Exception e) {

        }

        if (dataBaseCrypto != null && crypto.symbol.equals(dataBaseCrypto.symbol)){
            CryptoCurrency updateCrypto = cryptoService.updateCrypto(crypto.symbol,crypto.price);
            return ResponseEntity.ok().body(CryptoDTO.from(updateCrypto));
        }else {
            CryptoCurrency newCrypto = cryptoService.createCrypto(crypto.symbol, crypto.price);
            return ResponseEntity.ok().body(CryptoDTO.from(newCrypto));
        }

    }

    @Operation(summary = "Gets and persists all cryptos related to the project scope")
    @GetMapping("/api/cryptos")
    public ResponseEntity<List<CryptoDTO>> getAllCryptosPrice() {
        List<CryptoCurrency> cryptoCurrencyList = new ArrayList<CryptoCurrency>();
        for (CryptoCurrency.Cryptos crypto_enum :CryptoCurrency.Cryptos.values()) {
            CryptoCurrency crypto = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + crypto_enum, CryptoCurrency.class);

            CryptoCurrency dataBaseCrypto = null;
            try{
                dataBaseCrypto = cryptoService.findBySymbolIs(crypto.symbol).stream().findFirst().get();}catch(Exception e) {
                //  Block of code to handle errors
            }

            if (dataBaseCrypto != null && crypto.symbol.equals(dataBaseCrypto.symbol)){
                CryptoCurrency updateCrypto = cryptoService.updateCrypto(crypto.symbol,crypto.price);
                cryptoCurrencyList.add(updateCrypto);
            }else {
                CryptoCurrency newCrypto = cryptoService.createCrypto(crypto.symbol, crypto.price);
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
}
