package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.TransactionNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest extends ServiceTest {

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


    @Test
    void getTransactionthanTransactionInvalid() throws Exception {
            Long invalidId = Long.valueOf(10);

            Exception myException = null;
            try{
                getTransaction(invalidId);
            }catch(Exception e){
                myException = e;
            }
        assertEquals(myException.getClass(), TransactionNotFoundException.class);

        }


    @Test
    void processTransactionWithEqualsUser(){

        Exception myException = null;
        try{
            transactionService.processTransaction(transaction, transaction.getUser());
        }catch(Exception e){
            myException = e;
        }
        assertEquals(myException.getClass(), InvalidDataAccessApiUsageException.class);

    }








}