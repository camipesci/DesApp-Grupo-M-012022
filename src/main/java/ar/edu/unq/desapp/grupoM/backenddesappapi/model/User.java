package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User  {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String address;

    private String password;

    private String cvu;

    private Integer wallet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(@NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String address,
                @NotNull String password, @NotNull String cvu, @NotNull Integer wallet) {
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
                                           String password, String cvu, Integer wallet){
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

    public Boolean validCvu ( String cvu){
        return cvu.length() == 22;
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

    public String getCvu() {
        return cvu;
    }

    public Integer getWallet() {
        return wallet;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }
}