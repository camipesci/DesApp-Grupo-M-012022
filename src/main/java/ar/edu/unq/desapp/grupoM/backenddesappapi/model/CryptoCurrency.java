package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;

public class CryptoCurrency {

    @NotNull
    private String nameCrypto;
    @NotNull
    private Double price;
    @NotNull
    private LocalDate price_date;


    public CryptoCurrency(@NotNull String name, @NotNull Double price, @NotNull LocalDate price_date) {
        this.nameCrypto = name;
        this.price = price;
        this.price_date = price_date;
    }

    public String getName() {
        return nameCrypto;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getPrice_date() {
        return price_date;
    }
}
