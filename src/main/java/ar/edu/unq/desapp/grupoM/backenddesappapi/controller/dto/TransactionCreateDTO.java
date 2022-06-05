package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionCreateDTO {

    public String cryptoSymbol;
    public Float amountOfCrypto;
    public String transactionType;
    public Long userId;

    public TransactionCreateDTO(String cryptoSymbol, Float amountOfCrypto, String transactionType, Long userId) {
        this.cryptoSymbol = cryptoSymbol;
        this.amountOfCrypto = amountOfCrypto;
        this.transactionType = transactionType;
        this.userId = userId;
    }


}