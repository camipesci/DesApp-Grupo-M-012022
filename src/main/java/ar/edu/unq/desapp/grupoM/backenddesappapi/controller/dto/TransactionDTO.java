package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TransactionDTO {
    @JsonProperty
    public CryptoDTO  cryptoCurrency;
    @JsonProperty
    public Double cryptoAmount;
    @JsonProperty
    public Double cryptoPrice;
    @JsonProperty
    public Double cryptoArsPrice;
    @JsonProperty
    public UserDTO user;
    @JsonProperty
    public Transaction.TransactionType transactionType;

    public static TransactionDTO from(Transaction transaction) {
        return new TransactionDTO(CryptoDTO.from(transaction.getCryptoCurrency()),transaction.cryptoAmount, transaction.cryptoPrice,
                                  transaction.cryptoArsPrice, UserDTO.from(transaction.getUser()),
                                  transaction.transactionType);
    }
/*
    public static List<TransactionDTO> from(List<CryptoCurrency> transactions) {
        List<TransactionDTO> transactionsDTOList = new ArrayList<TransactionDTO>();
        for (CryptoCurrency transaction : transactions)
        {
            TransactionDTO newUserDto = new TransactionDTO(transaction.symbol, transaction.price, transaction.price_date);
            transactionsDTOList.add(newUserDto);
        }
        return transactionsDTOList;
    }
*/
    public TransactionDTO( CryptoDTO cryptoCurrency,  Double cryptoAmount,  Double cryptoPrice,  Double cryptoArsPrice,
                           UserDTO user, Transaction.TransactionType transactionType) {
        this.cryptoCurrency = cryptoCurrency;
        this.cryptoAmount = cryptoAmount;
        this.cryptoPrice = cryptoPrice;
        this.cryptoArsPrice = cryptoArsPrice;
        this.user = user;
        this.transactionType = transactionType;

    }
}