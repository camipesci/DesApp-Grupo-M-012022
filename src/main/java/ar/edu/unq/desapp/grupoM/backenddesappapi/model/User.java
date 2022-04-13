package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import java.math.BigInteger;

public class User  {

    private String name;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private BigInteger cvu;
    private Integer wallet;


    public User(String name, String lastName, String email, String address, String password, BigInteger cvu, Integer wallet) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.cvu = cvu;
        this.wallet = wallet;
    }


    public BigInteger getCvu() {
        return cvu;
    }
}