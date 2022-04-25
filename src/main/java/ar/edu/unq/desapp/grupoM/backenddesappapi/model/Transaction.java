package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import org.jetbrains.annotations.NotNull;

import java.util.Enumeration;

public class Transaction {

    @NotNull
    private String  cryptoCurrency;
    @NotNull
    private Float cryptoAmount;
    @NotNull
    private Float cryptoPrice;
    @NotNull
    private Float cryptoArsPrice;
    @NotNull
    private String user;
    @NotNull Enumeration operation;

    public Transaction(@NotNull String cryptoCurrency, @NotNull Float cryptoAmount, @NotNull Float cryptoPrice
            , @NotNull Float cryptoArsPrice, @NotNull String user, @NotNull Enumeration operation) {
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

    public Float getCryptoAmount() {
        return cryptoAmount;
    }

    public Float getCryptoPrice() {
        return cryptoPrice;
    }

    public Float getCryptoArsPrice() {
        return cryptoArsPrice;
    }

    public String getUser() {
        return user;
    }

    public Enumeration getOperation() {
        return operation;
    }
}
