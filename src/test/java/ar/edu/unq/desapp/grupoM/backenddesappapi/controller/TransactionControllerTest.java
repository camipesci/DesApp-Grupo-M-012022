/*package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionControllerTest extends TransactionControllerHelper{

    @Test
    void createTransaction() throws IOException {
        TransactionDTO created_transaction = transactionController.createTransaction(create_transaction_data).getBody();

        assertEquals(created_transaction.getTransactionPrice(), create_transaction_data.getCryptoPrice());
        assertEquals(created_transaction.getUser(), create_transaction_data.getUserId());
        assertEquals(created_transaction.cryptoAmount, create_transaction_data.cryptoAmount);
        assertEquals(created_transaction.cryptoArsPrice, create_transaction_data.cryptoArsPrice);
        assertEquals(created_transaction.cryptoPrice, create_transaction_data.cryptoPrice);
        assertEquals(created_transaction.cryptoCurrency.symbol, create_transaction_data.getCryptoCurrency().symbol);
    }

}*/