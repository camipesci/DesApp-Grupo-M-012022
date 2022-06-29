package ar.edu.unq.desapp.grupoM.backenddesappapi.external_api;

import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@NoArgsConstructor
public class USDPriceAPI {
    public Double getUSDPriceDouble() throws IOException {
        // Setup url and credentials to hit usd api
        URL binance_url = new URL("https://api.estadisticasbcra.com/usd_of");
        HttpURLConnection http = (HttpURLConnection)binance_url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        // Token had to  be hardcoded because circle ci was breaking
        http.setRequestProperty("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODgwMDU2MDEsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJsdWNpYS1wZXNjaUBob3RtYWlsLmNvbSJ9.ulNkZFXON5vpWBl3tTArrKob5t0Euyw3Do9qO06hlp5UORf4RVwGumqiiyyrBE0eAeeWl282NyXSO-mnetk2zw");

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
