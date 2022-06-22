package ar.edu.unq.desapp.grupoM.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;


public class TransactionBuilder  {


    public Crypto crypto = CryptoBuilder.crypto().build();
    public Double cryptoAmount = 0.01;
    public Double cryptoPrice = 120.10;
    public Double cryptoArsPrice = 200.20;
    public User user = UserBuilder.user().build();
    public Transaction.Type type = Transaction.Type.PURCHASE;



    public static TransactionBuilder transaction(){ return new TransactionBuilder();}

    public Transaction build() {
        return new Transaction(crypto, cryptoAmount, cryptoPrice,cryptoArsPrice,user, type);
    }

    public TransactionBuilder withCryptoCurrency(Crypto aCrypto){
        crypto = aCrypto;
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

    public TransactionBuilder withUserAndCrypto(User aUser, Crypto aCrypto){
        user = aUser;
        crypto = aCrypto;
        cryptoAmount = 0.01;
        cryptoPrice = 120.10;
        cryptoArsPrice = 200.20;
        type = Transaction.Type.PURCHASE;
        return this;
    }




}
