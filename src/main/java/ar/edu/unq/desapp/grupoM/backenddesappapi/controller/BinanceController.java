package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class BinanceController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api/crypto")
    public ResponseEntity<CryptoCurrency> getCryptoPrice() {

        CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=BNBUSDT", CryptoCurrency.class);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if(entity!= null){
            entity.price_date= date;
        }

        return ResponseEntity.ok().body(entity);


    }
}
