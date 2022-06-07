package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.UserNotFoundException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CurrencyService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.mock.MockUserGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    MockUserGenerateService dummyUserGenerateService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/transactions")
    @ResponseBody
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionCreateDTO transactionCreateDTO) throws IOException {
        CryptoCurrency crypto = currencyService.findBySymbolIs(transactionCreateDTO.cryptoSymbol).get(0);
        User user = userService.findUser(transactionCreateDTO.userId);


        Transaction transaction = transactionService.createTransaction(crypto,
                transactionCreateDTO.amountOfCrypto, crypto.price, crypto.getArsPrice(),
                user, getTransactionType(transactionCreateDTO.transactionType));
       // TODO: transaction.state = PENDING;
        // TODO:precio_postman: 2.1  precio sistema: 2.0
        // TODO: if cryptoHasMarginValuePrice(transactionCreateDTO.cryptoPrice, crypto.price)

                 // TODO: else transaction.cancel
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionDTO.from(transaction));
    }

    @GetMapping("/api/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<Transaction> transactions = transactionService.getTransactions();
        return ResponseEntity.ok().body(TransactionDTO.from(transactions));
    }

    @GetMapping("/api/transactions/{transaction_id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long transaction_id) throws Exception {
        Transaction transaction = transactionService.findTransaction(transaction_id);
        return ResponseEntity.ok().body(TransactionDTO.from(transaction));
    }

    public Transaction.TransactionType getTransactionType(String type){
        Transaction.TransactionType transactionType = null;
        switch (type.toLowerCase()){
            case "purchase":
                transactionType = Transaction.TransactionType.PURCHASE;
                break;
            case "sale":
                transactionType = Transaction.TransactionType.SALE;
                break;
        }

        return transactionType;
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    // This is the exception raised when we try to find a crypto by its symbol/name from user CurrencyService. It should have a custom exception
    public ResponseEntity handleException(IndexOutOfBoundsException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Crypto not found");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleException(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }
}
