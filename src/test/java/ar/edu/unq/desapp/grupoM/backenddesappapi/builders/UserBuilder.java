package ar.edu.unq.desapp.grupoM.backenddesappapi.builders;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;

import java.math.BigInteger;

public class UserBuilder {

    private String name = "Ricardo";
    private String lastname = "Fort";
    private String email = "ricardoFort@gmail.com";
    private String address = "Av. Mitre 1030";
    private String password = "Ricardo123@";
    private String cvu = "1234567890987654321234";
    private Integer wallet = 12345678;

    public static UserBuilder user(){ return new UserBuilder();}

    public User build() {
        return new User(name, lastname, email, address, password, cvu, wallet);
    }

    public UserBuilder withName(String aName){
        name = aName;
        return this;
    }

    public UserBuilder withLastname(String aLastname){
        lastname = aLastname;
        return this;
    }

    public UserBuilder withEmail(String aEmail){
        email = aEmail;
        return this;
    }

    public UserBuilder withAddress(String aAddress){
        address = aAddress;
        return this;
    }

    public UserBuilder withPassword(String aPassword){
        password = aPassword;
        return this;
    }

    public UserBuilder withCvu(String aCvu){
        cvu = aCvu;
        return this;
    }

    public UserBuilder withWallet(Integer aWallet){
        wallet = aWallet;
        return this;
    }



}
