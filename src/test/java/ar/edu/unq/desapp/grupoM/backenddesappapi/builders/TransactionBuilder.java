package ar.edu.unq.desapp.grupoM.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;

import java.util.Date;
import java.util.Enumeration;

public class TransactionBuilder  {


    private String cryptoCurrency = "BNBUSDT";
    private Double cryptoAmount = 0.01;
    private Double cryptoPrice = 120.10;
    private Double cryptoArsPrice = 200.20;
    private Long user = UserBuilder.user().build().id;
    private Date date = null;
    private String transactionType = null;


    public static TransactionBuilder transaction(){ return new TransactionBuilder();}

    public Transaction build() {
        return new Transaction(cryptoCurrency, cryptoAmount, cryptoPrice,cryptoArsPrice,user,date,transactionType);
    }

    public TransactionBuilder withCryptoCurrency(String aCryptoCurrency){
        cryptoCurrency = aCryptoCurrency;
        return this;
    }

    public TransactionBuilder withCryptoAmount(Double aCryptoAmount){
        cryptoAmount = aCryptoAmount;
        return this;
    }

    public TransactionBuilder withCryptoPrice(Double aCryptoPrice){
        cryptoPrice = aCryptoPrice;
        return this;
    }

    public TransactionBuilder withCryptoArsPrice(Double aCryptoArsPrice){
        cryptoArsPrice = aCryptoArsPrice;
        return this;
    }

     public TransactionBuilder withUser(User aUser){
        user = aUser.id;
        return this;
    }




}
