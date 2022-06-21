package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest extends TransactionServiceHelper {

    @Test
    void getTransactions() {
       assertEquals(transactionService.getTransactions().get(0).getId(), h2_transaction.getId());
       assertEquals(transactionService.getTransactions().size(), 1);
    }

    @Test
    void findTransaction() throws Exception {
        assertEquals(transactionService.findTransaction(h2_transaction.getId()).getId(), h2_transaction.getId());
        assertEquals(transactionService.getTransactions().size(), 1);
    }
}