package ar.edu.unq.desapp.grupoM.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;


public class TransactionBuilder  {


    public CryptoCurrency cryptoCurrency = CryptoBuilder.crypto().build();
    public Double cryptoAmount = 0.01;
    public Double cryptoPrice = 120.10;
    public Double cryptoArsPrice = 200.20;
    public User user = UserBuilder.user().build();
    public Transaction.Type type = Transaction.Type.PURCHASE;



    public static TransactionBuilder transaction(){ return new TransactionBuilder();}

    public Transaction build() {
        return new Transaction(cryptoCurrency, cryptoAmount, cryptoPrice,cryptoArsPrice,user, type);
    }

    public TransactionBuilder withCryptoCurrency(CryptoCurrency aCryptoCurrency){
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
        user = aUser;
        return this;
    }




}
