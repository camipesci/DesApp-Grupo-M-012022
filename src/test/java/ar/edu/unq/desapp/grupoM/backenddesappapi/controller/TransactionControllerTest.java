package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionControllerTest extends ControllerTest {

    @Test
    void createTransactionAndfindTransactionById() {

        TransactionCreateDTO transactionCreateDTO = createTransactionDTO();

        TransactionDTO responseCreateTransaction = createTransaction(transactionCreateDTO);


       TransactionDTO responseFindTransaction = findTransactionById(responseCreateTransaction.getId());

     //   assertThat(responseCreateTransaction.getCryptoCurrency()).isEqualTo(transactionCreateDTO.getCryptoSymbol());

     //   assertThat(responseFindTransaction.getCryptoCurrency()).isEqualTo(transactionCreateDTO.getCryptoSymbol());
    }

    @Test
    public void createTransactionFailsWithUserIsNotFound() {
        Long notRegisteredUserId = -1L;
        TransactionCreateDTO transactionCreateDTO = new TransactionCreateDTO("BNB", 100.0, 100.1, "Purchase", notRegisteredUserId);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(transactionURL(), new HttpEntity(transactionCreateDTO), String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }



}