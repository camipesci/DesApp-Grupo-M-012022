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
