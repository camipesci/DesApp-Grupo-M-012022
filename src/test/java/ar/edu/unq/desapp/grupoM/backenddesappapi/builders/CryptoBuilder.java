package ar.edu.unq.desapp.grupoM.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import java.util.Date;


public class CryptoBuilder {


    private String nameCrypto = "BNBUSDT";
    private Double price = 120.10;
    private Date price_date = new Date();


    public static CryptoBuilder crypto(){ return new CryptoBuilder();}

    public Crypto build() {
        return new Crypto(nameCrypto, price, price_date);
    }

    public CryptoBuilder withNameCrypto(String aName){
        nameCrypto = aName;
        price = 120.10;
        price_date = new Date();
        return this;
    }

    public CryptoBuilder withPrice(Double aPrice){
        price = aPrice;
        return this;
    }

    public CryptoBuilder withPrice_date(Date aPrice_date){
        price_date = aPrice_date;
        return this;
    }



}
