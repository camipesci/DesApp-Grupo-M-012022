package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.util.Enumeration;

@NoArgsConstructor
public class CryptoCurrency {

    @NotNull
    public String symbol;
    @NotNull
    public Double price;
    @NotNull
    public Date price_date;

    public enum Cryptos {
            ALICEUSDT,
            MATICUSDT,
            AXSUSDT,
            AAVEUSDT,
            ATOMUSDT,
            NEOUSDT,
            DOTUSDT,
            ETHUSDT,
            CAKEUSDT,
            BTCUSDT,
            BNBUSDT,
            ADAUSDT,
            TRXUSDT,
            AUDIOUSDT
        }


    public CryptoCurrency(@NotNull String symbol, @NotNull Double price, @NotNull Date price_date) {
        this.symbol = symbol;
        this.price = price;
        this.price_date = price_date;
    }

    public String getName() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    public Date getPrice_date() {
        return price_date;
    }
}
