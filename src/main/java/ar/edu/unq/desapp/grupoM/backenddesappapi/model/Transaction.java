package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import lombok.Builder;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CryptoCurrency  cryptoCurrency;
    @NotNull
    public Double cryptoAmount;
    @NotNull
    public Double cryptoPrice;
    @NotNull
    public Double cryptoArsPrice;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @NotNull
    private User user;
    @NotNull
    public Transaction.TransactionType transactionType;

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum TransactionType {
        COMPRA,
        VENTA
    }

    public Transaction(@NotNull CryptoCurrency cryptoCurrency, @NotNull Double cryptoAmount, @NotNull Double cryptoPrice
            , @NotNull Double cryptoArsPrice, @NotNull User user, @NotNull  Transaction.TransactionType transactionType) {
        this.cryptoCurrency = cryptoCurrency;
        this.cryptoAmount = cryptoAmount;
        this.cryptoPrice = cryptoPrice;
        this.cryptoArsPrice = cryptoArsPrice;
        this.user = user;
        this.transactionType = transactionType;
    }

    public void calculateCryptoArsPrice(){
       // Double usdPrice =  USDPriceController.getUsdPrice();
        //this.cryptoArsPrice = this.cryptoPrice * usdPrice;
    }


}
