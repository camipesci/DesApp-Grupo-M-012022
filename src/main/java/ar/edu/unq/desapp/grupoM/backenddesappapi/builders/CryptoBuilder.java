package ar.edu.unq.desapp.grupoM.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import java.util.Date;


public class CryptoBuilder {


    private String nameCrypto = "BNBUSDT";
    private Double price = 2.0;
    private Date price_date = new Date();


    public static CryptoBuilder crypto(){ return new CryptoBuilder();}

    public CryptoCurrency build() {
        return new CryptoCurrency(nameCrypto, price, price_date);
    }

    public CryptoBuilder withNameCrypto(String aName){
        nameCrypto = aName;
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
