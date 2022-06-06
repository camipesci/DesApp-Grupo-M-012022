package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



@RestController
@NoArgsConstructor
public class USDPriceController {

    @GetMapping("/api/usd_price")
    public ResponseEntity<String> getUSDPrice() throws IOException {
        URL url = new URL("https://api.estadisticasbcra.com/usd_of");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODU2NjA0MDYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJjYW1pbGEucGVzY2kuYUBnbWFpbC5jb20ifQ.F-5_ZVANljRvZ3qdfA9R-P2LH0PiuBJmOJOkmMJoPDiFeShlVK_FiQ0UDCOrRAthBKER2B0y98jGH_QibkUwQw");

        String response = this.getResponseBody(http);
        JSONArray usd_prices_array = new JSONArray(response);
        JSONObject today_usd_price = new JSONObject();
        JSONObject usd_price = (JSONObject) usd_prices_array.get(usd_prices_array.length() -1);
        Double price_value = usd_price.getDouble("v");
        today_usd_price.put("today_usd_price",  usd_prices_array.get(usd_prices_array.length() -1));
        return ResponseEntity.ok().body(price_value.toString());

    }

    public Double getUSDPriceDouble() throws IOException {
        URL binance_url = new URL("https://api.estadisticasbcra.com/usd_of");
        HttpURLConnection http = (HttpURLConnection)binance_url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODU2NjA0MDYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJjYW1pbGEucGVzY2kuYUBnbWFpbC5jb20ifQ.F-5_ZVANljRvZ3qdfA9R-P2LH0PiuBJmOJOkmMJoPDiFeShlVK_FiQ0UDCOrRAthBKER2B0y98jGH_QibkUwQw");

        String response = this.getResponseBody(http);
        JSONArray usd_prices_array = new JSONArray(response);
        JSONObject today_usd_price = new JSONObject();
        JSONObject usd_price = (JSONObject) usd_prices_array.get(usd_prices_array.length() -1);
        Double price_value = usd_price.getDouble("v");
        return price_value;

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