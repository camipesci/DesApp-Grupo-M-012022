package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionDTO;
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
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionCreateDTO newTransactionDTO) throws IOException {
        CryptoCurrency crypto = currencyService.findBySymbolIs(newTransactionDTO.cryptoSymbol).get(0);
        User user = userService.findUser(newTransactionDTO.userId);

        Transaction transaction = transactionService.createTransaction(crypto,
                newTransactionDTO.amountOfCrypto, crypto.price, crypto.getArsPrice(),
                user, getTransactionType(newTransactionDTO.transactionType));
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionDTO.from(transaction));
    }

    public Transaction.TransactionType getTransactionType(String type){
        Transaction.TransactionType transactionType = null;
        switch (type.toLowerCase()){
            case "compra":
                transactionType = Transaction.TransactionType.COMPRA;
                break;
            case "venta":
                transactionType = Transaction.TransactionType.VENTA;
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
