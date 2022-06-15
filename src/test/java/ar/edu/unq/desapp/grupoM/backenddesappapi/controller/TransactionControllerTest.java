/*package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionControllerTest extends TestControllerHelper{

    @Test
    void createTransaction() {
        TransactionDTO created_transaction = transactionController.createTransaction(create_transaction_data).getBody();

        assertEquals(created_transaction.transactionType.name(), create_transaction_data.transactionType.name());
        assertEquals(created_transaction.user.name, create_transaction_data.getUser().name);
        assertEquals(created_transaction.cryptoAmount, create_transaction_data.cryptoAmount);
        assertEquals(created_transaction.cryptoArsPrice, create_transaction_data.cryptoArsPrice);
        assertEquals(created_transaction.cryptoPrice, create_transaction_data.cryptoPrice);
        assertEquals(created_transaction.cryptoCurrency.symbol, create_transaction_data.getCryptoCurrency().symbol);
    }

}*/