package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class CryptoCurrency {
    @NotNull
    private String name;
    @NotNull
    private Float price;
    @NotNull
    private Date price_date;



    public CryptoCurrency(@NotNull String name, @NotNull Float price, @NotNull Date price_date) {
        this.name = name;
        this.price = price;
        this.price_date = price_date;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Date getPrice_date() {
        return price_date;
    }
}
