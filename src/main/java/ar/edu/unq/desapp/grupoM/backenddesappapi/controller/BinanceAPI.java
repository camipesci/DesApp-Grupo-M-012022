package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.DatabaseInitializate;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BinanceAPI {

    RestTemplate restTemplate = new RestTemplate();

    Logger log = LoggerFactory.getLogger(DatabaseInitializate.class);


    public Crypto call(String symbol){
        Crypto crypto = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, Crypto.class);
        log.info("Updating " + symbol + " price from Binance api");
        return crypto;
    }

}
