package ar.edu.unq.desapp.grupoM.backenddesappapi.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TransactionDTO {
    @JsonProperty
    public Long id;
    @JsonProperty
    public String  cryptoCurrency;
    @JsonProperty
    public Double cryptoAmount;
    @JsonProperty
    public Double cryptoPrice;
    @JsonProperty
    public Double cryptoArsPrice;
    @JsonProperty
    public Double transactionPrice;
    @JsonProperty
    public String user;
    @JsonProperty
    public Transaction.Type type;
    @JsonProperty
    public Transaction.Status status;
    @JsonProperty
    public String date;

    public static TransactionDTO from(Transaction transaction) {
        return new TransactionDTO(CryptoDTO.from(transaction.getCrypto()),transaction.cryptoAmount, transaction.cryptoPrice,
                                  transaction.cryptoArsPrice, UserDTO.from(transaction.getUser()),
                                  transaction.type, transaction.status, transaction.getId());
    }

    public static List<TransactionDTO> from(List<Transaction> transactions) {
        List<TransactionDTO> transactionsDTOList = new ArrayList<TransactionDTO>();
        for (Transaction transaction : transactions)
        {
            TransactionDTO transactionDTO = new TransactionDTO(CryptoDTO.from(transaction.getCrypto()),transaction.cryptoAmount, transaction.cryptoPrice,
                    transaction.cryptoArsPrice, UserDTO.from(transaction.getUser()),
                    transaction.type, transaction.status, transaction.getId());
            transactionsDTOList.add(transactionDTO);
        }
        return transactionsDTOList;
    }

    public TransactionDTO(CryptoDTO cryptoCurrency, Double cryptoAmount, Double cryptoPrice, Double cryptoArsPrice,
                          UserDTO user, Transaction.Type type, Transaction.Status status, Long id) {
        this.id = id;
        this.cryptoCurrency = cryptoCurrency.getSymbol();
        this.cryptoAmount = cryptoAmount;
        this.cryptoPrice = cryptoPrice;
        this.cryptoArsPrice = cryptoArsPrice;
        this.user = user.getCompleteName();
        this.type = type;

        // custom extra fields
        this.transactionPrice =  cryptoArsPrice * cryptoAmount;
        this.date = formatted_date();
        this.status = status;

    }

    public String formatted_date(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date raw_date = new Date();
        String formatted_date = formatter.format(raw_date);
        return formatted_date;
    }


    }