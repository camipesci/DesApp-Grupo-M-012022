package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



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



    @GetMapping("/api/usd_price")
    public ResponseEntity<?> getUSDPrice() throws IOException {
        URL url = new URL("https://api.estadisticasbcra.com/usd_of");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODU2NjA0MDYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJjYW1pbGEucGVzY2kuYUBnbWFpbC5jb20ifQ.F-5_ZVANljRvZ3qdfA9R-P2LH0PiuBJmOJOkmMJoPDiFeShlVK_FiQ0UDCOrRAthBKER2B0y98jGH_QibkUwQw");

        return ResponseEntity.ok().body(this.getResponseBody(http));
    }

    public static String getResponseBody(HttpURLConnection conn) {
        BufferedReader br = null;
        StringBuilder body = null;
        String line = "";
        try {//  w  w w .j  a  v a 2s . c  o m
            br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            body = new StringBuilder();
            while ((line = br.readLine()) != null)
                body.append(line);
            return body.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}