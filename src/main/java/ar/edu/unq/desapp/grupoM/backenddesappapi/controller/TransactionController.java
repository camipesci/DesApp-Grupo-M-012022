package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.UserNotFoundException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.mock.MockUserGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    MockUserGenerateService dummyUserGenerateService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/api/transactions")
    @ResponseBody
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction newTransaction) {
        Transaction transaction = transactionService.createTransaction(newTransaction.getCryptoCurrency(),
                newTransaction.cryptoAmount, newTransaction.cryptoPrice, newTransaction.cryptoArsPrice,
                newTransaction.getUser(), newTransaction.transactionType);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

/*
    @PostMapping(path="/addTransaction/user={userID}")
    public TransactionDTO createTransaction( @PathVariable Long userID, @RequestBody TransactionCreateDTO transaction) {
        Transaction savedTransaction = transactionService.createTransaction(transaction, userID);

        String username = savedTransaction.getUser().getName() + " " + savedTransaction.getUser().getSurname();
        Integer operationNumber = savedTransaction.getUser().getOperationsNumber();
        String score = savedTransaction.getUser().getScore();
        CryptoCurrency crypto = savedTransaction.getCrypto();

        return new TransactionDTO(savedTransaction.getId(), savedTransaction.getDateAndTime(),
                crypto.getSymbol(), crypto.getAmount(),
                crypto.getPrice(), crypto.getPriceInARS(),
                savedTransaction.getTransactionType(), username, operationNumber, score);
    }
*/

    }
