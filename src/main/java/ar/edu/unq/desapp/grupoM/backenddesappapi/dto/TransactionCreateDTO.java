package ar.edu.unq.desapp.grupoM.backenddesappapi.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionCreateDTO {

    public String cryptoSymbol;
    public Double amountOfCrypto;
    public Double cryptoPrice;
    public String transactionType;
    public Long userId;

    public TransactionCreateDTO(String cryptoSymbol, Double amountOfCrypto, Double cryptoPrice, String transactionType, Long userId) {
        this.cryptoSymbol = cryptoSymbol;
        this.amountOfCrypto = amountOfCrypto;
        this.cryptoPrice = cryptoPrice;
        this.transactionType = transactionType;
        this.userId = userId;
    }

    public static TransactionCreateDTO from (Transaction transaction) {
        return new TransactionCreateDTO(transaction.getCrypto().getSymbol(),
        transaction.getCryptoAmount(),
        transaction.getCryptoPrice(),
        transaction.getType().toString(), transaction.getUserId().getUser_id());

    }


}