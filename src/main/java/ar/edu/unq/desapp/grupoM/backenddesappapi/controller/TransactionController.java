package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.*;
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
        Transaction transaction = null;

        if(cryptoHasMarginValuePrice(transactionCreateDTO.cryptoPrice, crypto.price)){
            transaction = createTransaction(transactionCreateDTO, user, crypto);
        }else {
            transaction = createTransaction(transactionCreateDTO, user, crypto);
            transactionService.updateTransactionStatus(transaction, Transaction.Status.CANCELED);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionDTO.from(transaction));
    }

    public Boolean cryptoHasMarginValuePrice(Double user_price, Double crypto_price){
        Double result = 0.0;
        result = 100 * ((user_price - crypto_price) / crypto_price);
        Double positive_result = Math.abs(result);

        return positive_result < 5;
    }

    @GetMapping("/api/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<Transaction> transactions = transactionService.getTransactionsByStatus(Transaction.Status.PENDING);
        return ResponseEntity.ok().body(TransactionDTO.from(transactions));
    }

    @GetMapping("/api/transactions/{transaction_id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long transaction_id) throws Exception {
        Transaction transaction = transactionService.findTransaction(transaction_id);
        return ResponseEntity.ok().body(TransactionDTO.from(transaction));
    }

    @GetMapping("/api/transactions/users/{user_id}")
    public ResponseEntity<TransactionDTO> getTransactionByUser(@PathVariable Long user_id) throws Exception {
        User user = userService.findUser(user_id);
        Transaction transaction = transactionService.findTransactionByUser(user);
        return ResponseEntity.ok().body(TransactionDTO.from(transaction));
    }

    @GetMapping("/api/transactions/process/{transaction_id}/user/{user_id}")
    public ResponseEntity<ProcessedTransactionDTO> processTransaction(@PathVariable Long transaction_id, @PathVariable Long user_id) throws Exception {
        Transaction transaction = transactionService.findTransaction(transaction_id);
        User interested_user = userService.findUser(user_id);
        transactionService.processTransaction(transaction, interested_user);
        // i bring the user again with the transaction and operations values updated
        interested_user = userService.findUser(user_id);
        UserDTO interested_user_dto = UserDTO.from(interested_user);
        return ResponseEntity.ok().body(ProcessedTransactionDTO.from(transaction,interested_user_dto ));
    }

    @PostMapping("/api/transactions/users/{user_id}/traded_volumes")
    public ResponseEntity<UserTradedVolumenDTO> getTradedVolume(@PathVariable Long user_id, @RequestBody DatesDTO dates){

        return ResponseEntity.ok().body(transactionService.getTradedVolumes(dates,user_id));
    }

    // Auxiliar methods

    public Transaction.Type getTransactionType(String type){
        Transaction.Type transactionType = null;
        switch (type.toLowerCase()){
            case "purchase":
                transactionType = Transaction.Type.PURCHASE;
                break;
            case "sale":
                transactionType = Transaction.Type.SALE;
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

    public Transaction createTransaction(TransactionCreateDTO transactionCreateDTO, User user, CryptoCurrency crypto) throws IOException {
        Transaction transaction = transactionService.createTransaction(crypto,
                transactionCreateDTO.amountOfCrypto, crypto.price, crypto.getArsPrice(),
                user, getTransactionType(transactionCreateDTO.transactionType));
        return transaction;
    }
}
