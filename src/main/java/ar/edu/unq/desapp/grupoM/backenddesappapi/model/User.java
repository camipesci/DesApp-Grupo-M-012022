package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;


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



    public User(String name, String lastName, String email, String address, String password, BigInteger cvu, Integer wallet) {
        //if (this.isValidUser()) {
            this.name = name;
            this.lastName = lastName;
            this.email = email;
            this.address = address;
            this.password = password;
            this.cvu = cvu;
            this.wallet = wallet;
       // }else
        //{raise IvalidUserException}
    }

  // public Boolean isValidUser(){
 //       return;
 //   }



    public Boolean validNameOrLastName (String name){

        return name.length() > 3 && name.length() < 30;
    }

    public Boolean validEmail (){
        return this.email.matches("(\\S.\\S)(@)(\\S.\\S)(.\\S[a-z]{2,3})");
    }

    public Boolean validAddress (){

        return this.address.length() > 10 && address.length() < 30;
    }

    public Boolean validCvu (){

        return this.cvu.toString().length() == 22;
    }

    public Boolean validWallet (){

        return this.wallet.toString().length()== 8;
    }

    public Boolean validPassword (){

      return this.password.matches("^(?=.\\d)(?=.[a-z])(?=.[A-Z])(?=.[@#$%]).{6,30}$");
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
}