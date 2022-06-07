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
    public Double transactionPrice;
    @JsonProperty
    public UserDTO user;
    @JsonProperty
    public Transaction.TransactionType transactionType;
    @JsonProperty
    public String date;

    public static TransactionDTO from(Transaction transaction) {
        return new TransactionDTO(CryptoDTO.from(transaction.getCryptoCurrency()),transaction.cryptoAmount, transaction.cryptoPrice,
                                  transaction.cryptoArsPrice, UserDTO.from(transaction.getUser()),
                                  transaction.transactionType);
    }

    public static List<TransactionDTO> from(List<Transaction> transactions) {
        List<TransactionDTO> transactionsDTOList = new ArrayList<TransactionDTO>();
        for (Transaction transaction : transactions)
        {
            TransactionDTO transactionDTO = new TransactionDTO(CryptoDTO.from(transaction.getCryptoCurrency()),transaction.cryptoAmount, transaction.cryptoPrice,
                    transaction.cryptoArsPrice, UserDTO.from(transaction.getUser()),
                    transaction.transactionType);
            transactionsDTOList.add(transactionDTO);
        }
        return transactionsDTOList;
    }

    public TransactionDTO( CryptoDTO cryptoCurrency,  Double cryptoAmount,  Double cryptoPrice,  Double cryptoArsPrice,
                           UserDTO user, Transaction.TransactionType transactionType) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date raw_date = new Date();
        String formatted_date = formatter.format(raw_date);
        // TODO: wallet = user.wallet if tipo purchase else null
        // TODO: cvu = user.wallet if tipo sale else null
        // TODO:  if transactionType compra =
        this.cryptoCurrency = cryptoCurrency;
        this.cryptoAmount = cryptoAmount;
        this.cryptoPrice = cryptoPrice;
        this.cryptoArsPrice = cryptoArsPrice;
        this.transactionPrice =  cryptoArsPrice * cryptoAmount;
        this.user = user;
        this.transactionType = transactionType;
        this.date = formatted_date;
        // TODO: this.wallet;
        // TODO: this.cvu;

    }
}