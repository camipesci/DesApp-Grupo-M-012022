package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest extends TransactionServiceHelper {

    @Test
    void getTransactions() {
       assertEquals(transactionQueryService.getTransactions().get(0).getId(), h2_transaction.getId());
       assertEquals(transactionQueryService.getTransactions().size(), 1);
    }

    @Test
    void findTransaction() throws Exception {
        assertEquals(transactionQueryService.findTransaction(h2_transaction.getId()).getId(), h2_transaction.getId());
        assertEquals(transactionQueryService.getTransactions().size(), 1);
    }
}