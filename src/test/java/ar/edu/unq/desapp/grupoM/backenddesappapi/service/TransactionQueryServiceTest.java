package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionQueryServiceTest extends TestServiceHelper {

    @Test
    void getTransactions() {
       assertEquals(transactionQueryService.getTransactions().get(0).getCryptoCurrency(), transaction.getCryptoCurrency());
       assertEquals(transactionQueryService.getTransactions().size(), 2);
    }

    @Test
    void findTransaction() throws Exception {
        assertEquals(transactionQueryService.findTransaction(h2_transaction.id).getUserid(), h2_transaction.getUserid());
        assertEquals(transactionQueryService.getTransactions().size(), 1);
    }
}