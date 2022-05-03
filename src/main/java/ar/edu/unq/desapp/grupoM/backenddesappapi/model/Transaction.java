package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import org.jetbrains.annotations.NotNull;
import java.util.Enumeration;

public class Transaction {

    @NotNull
    private String  cryptoCurrency;
    @NotNull
    private Double cryptoAmount;
    @NotNull
    private Double cryptoPrice;
    @NotNull
    private Double cryptoArsPrice;
    @NotNull
    private User user;
    @NotNull
    Enumeration operation;

    public Transaction(@NotNull String cryptoCurrency, @NotNull Double cryptoAmount, @NotNull Double cryptoPrice
            , @NotNull Double cryptoArsPrice, @NotNull User user, @NotNull Enumeration operation) {
        this.cryptoCurrency = cryptoCurrency;
        this.cryptoAmount = cryptoAmount;
        this.cryptoPrice = cryptoPrice;
        this.cryptoArsPrice = cryptoArsPrice;
        this.user = user;
        this.operation = operation;
    }

    public String getCryptoCurrency() {
        return cryptoCurrency;
    }

    public Double getCryptoAmount() {
        return cryptoAmount;
    }

    public Double getCryptoPrice() {
        return cryptoPrice;
    }

    public Double getCryptoArsPrice() {
        return cryptoArsPrice;
    }

    public User getUser() {
        return user;
    }

    public Enumeration getOperation() {
        return operation;
    }
}
