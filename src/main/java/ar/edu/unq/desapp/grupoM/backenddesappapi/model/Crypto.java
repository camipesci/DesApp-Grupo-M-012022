package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.USDPriceController;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;

@Entity
@Table(name = "cryptos")
@Data
@NoArgsConstructor
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crypto_id")
    private Long crypto_id;
    @NotNull
    public String symbol;
    @NotNull
    public Double price;
    @NotNull
    public Date price_date;


    public Long getId() {
        return crypto_id;
    }

    public void setId(Long crypto_id) {
        this.crypto_id = crypto_id;
    }

    public Double getArsPrice() throws IOException {
        USDPriceController usdPriceController = new USDPriceController();
        Double usd_price = usdPriceController.getUSDPriceDouble();
        return usd_price * price;
    }

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


    public Crypto(@NotNull String symbol, @NotNull Double price, @NotNull Date price_date) {
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
