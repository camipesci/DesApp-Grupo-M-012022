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
    public Long id;
    @NotNull
    private String  cryptoCurrency;
    @NotNull
    private Double cryptoAmount;
    @NotNull
    private Double cryptoPrice;
    @NotNull
    private Double cryptoArsPrice;
    @NotNull
    private Long userid;
    @NotNull
    private Date date;
    @NotNull
    private String transactionType;

    public Transaction(@NotNull String cryptoCurrency, @NotNull Double cryptoAmount, @NotNull Double cryptoPrice
            , @NotNull Double cryptoArsPrice, @NotNull Long userid,@NotNull Date date, @NotNull String transactionType) {
        this.cryptoCurrency = cryptoCurrency;
        this.cryptoAmount = cryptoAmount;
        this.cryptoPrice = cryptoPrice;
        this.cryptoArsPrice = cryptoArsPrice;
        this.userid = userid;
        this.date = date;
        this.transactionType = transactionType;
    }

    public String getCryptoCurrency() {
        return cryptoCurrency;
    }

    public Double getCryptoAmount() {
        return cryptoAmount;
    }

    public Double getCryptoPrice() {
        return cryptoPrice;
    }

    public Double getCryptoArsPrice() {
        return cryptoArsPrice;
    }

    public Long getUserId() {
        return this.userid;
    }

    public String gettransactionType() {
        return transactionType;
    }
}
