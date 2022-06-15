package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@NoArgsConstructor
public class USDPriceAPI {
    public Double getUSDPriceDouble() throws IOException {
        // Setup url and credentials to hit usd api
        URL binance_url = new URL("https://api.estadisticasbcra.com/usd_of");
        HttpURLConnection http = (HttpURLConnection)binance_url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODY3OTg3NjEsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJmbGVtaXRhcHJvQGdtYWlsLmNvbSJ9.WZz_UgeOpOgaNYNhuM3e6KoctscWaaRuaBugSf8tbHz92JZW2oYpJG-iDyH3B2VcodPj4snkPnq83fPnS8ynqw");

        // Proccess USD API Response
        String response = this.getResponseBody(http);

        // Get array of dolar prices
        JSONArray usd_prices_array = new JSONArray(response);

        // Get newest dolar price (last element)
        JSONObject today_usd_price = (JSONObject) usd_prices_array.get(usd_prices_array.length() -1);

        // Get the dolar price value
        Double usd_price_value = today_usd_price.getDouble("v");

        return usd_price_value;
    }

    public static String getResponseBody(HttpURLConnection conn) {
        // This is a method that get the URLConnection responses to a legible format
        BufferedReader br = null;
        StringBuilder body = null;
        String line = "";
        try {
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
