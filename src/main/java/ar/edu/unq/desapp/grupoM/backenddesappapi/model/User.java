package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.*;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.helpers.Generator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    public String name;

    public String lastName;

    public String email;

    public String address;

    public String password;

    public String cvu;

    public Integer wallet;

    public Integer operations;

    public Integer score;

    public Long getId() {
        return user_id;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public void generateWalletAndCVU(){
        Generator random = new Generator();
        this.cvu = random.generateCVU();
        this.wallet = random.generateWallet();
    }

    public User(@NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String address,
                @NotNull String password) {
        this.generateWalletAndCVU();
        this.validateUserParameters(name, lastName, email, address, password);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.operations = 0;
        this.score = 0;

    }


    // Validations

    public  void validateUserParameters(String name, String lastName, String email, String address, String password){
        if (!this.validNameOrLastName(name)) {  throw new InvalidNameException(name); }
        if (!this.validNameOrLastName(lastName)) {  throw new InvalidLastNameException(lastName); }
        if (!this.validEmail(email)) {  throw new InvalidEmailException(email); }
        if (!this.validAddress(address)) {  throw new InvalidAddressException(address); }
        if (!this.validPassword(password)) {  throw new InvalidPasswordException(password); }
        if (!this.validCvu(this.cvu)) {  throw new InvalidCvuException(this.cvu); }
        if (!this.validWallet(this.wallet)) {  throw new InvalidWalletException(this.wallet); }
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

    public Boolean validCvu (String cvu){
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

    public String reputation(){
        if(this.score==0){
            return "No operations";
        }else{
            Integer reputation = this.score / this.operations;
            return reputation.toString();
        }
    }

}