package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CurrencyService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
public class USDPriceController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CurrencyService currencyService;



   /* private Transaction getUsdConvertionToPesos (){
        String url = "https://api.estadisticasbcra.com/usd";

        String base64Creds = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODU2NjA0MDYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJjYW1pbGEucGVzY2kuYUBnbWFpbC5jb20ifQ.F-5_ZVANljRvZ3qdfA9R-P2LH0PiuBJmOJOkmMJoPDiFeShlVK_FiQ0UDCOrRAthBKER2B0y98jGH_QibkUwQw";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + base64Creds);

        HttpEntity request = new HttpEntity(headers);

        final RestTemplate restTemplate = new RestTemplate();

        try {

            final ResponseEntity<Transaction> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    Transaction.class);

            Transaction conversions = response.getBody()[-1];

            return conversions;

        } catch (Exception exception) {
            throw new Error(exception);
        }
    }*/

}
