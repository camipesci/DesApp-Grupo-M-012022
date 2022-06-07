package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

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


}