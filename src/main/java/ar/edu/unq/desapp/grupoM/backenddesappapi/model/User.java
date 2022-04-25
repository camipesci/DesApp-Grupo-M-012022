package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.*;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User  {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String password;
    @NotNull
    private BigInteger cvu;
    @NotNull
    private Integer wallet;


    public User(String name, String lastName, String email, String address,
                String password, BigInteger cvu, Integer wallet) {

            this.validateUserParameters(name, lastName, email, address, password, cvu, wallet);
            this.name = name;
            this.lastName = lastName;
            this.email = email;
            this.address = address;
            this.password = password;
            this.cvu = cvu;
            this.wallet = wallet;
    }


    // Validations

    public  void validateUserParameters(String name, String lastName, String email, String address,
                                           String password, BigInteger cvu, Integer wallet){
        if (!this.validNameOrLastName(name)) {  throw new InvalidNameException(name); }
        if (!this.validNameOrLastName(lastName)) {  throw new InvalidLastNameException(lastName); }
        if (!this.validEmail(email)) {  throw new InvalidEmailException(email); }
        if (!this.validAddress(address)) {  throw new InvalidAddressException(address); }
        if (!this.validPassword(password)) {  throw new InvalidPasswordException(password); }
        if (!this.validCvu(cvu)) {  throw new InvalidCvuException(cvu); }
        if (!this.validWallet(wallet)) {  throw new InvalidWalletException(wallet); }
    }

    public Boolean validNameOrLastName (String name){

        return name.length() > 3 && name.length() < 30;
    }

    public Boolean validEmail(String username){
        String email_regexp  = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        Pattern pattern = Pattern
                .compile(email_regexp);

        Matcher mather = pattern.matcher(username);
        return mather.find();
    }

    public Boolean validAddress(String address){
        return address.length() > 10 && address.length() < 30;
    }

    public Boolean validCvu ( BigInteger cvu){
        return cvu.toString().length() == 22;
    }

    public Boolean validWallet (Integer wallet){
        return wallet.toString().length()== 8;
    }

    public Boolean validPassword(String username){
        Pattern pattern = Pattern
                .compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

        Matcher mather = pattern.matcher(username);
        return mather.find();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public BigInteger getCvu() {
        return cvu;
    }

    public Integer getWallet() {
        return wallet;
    }

    public String getPassword() {
        return password;
    }
}