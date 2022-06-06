package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CurrencyService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.mock.MockUserGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionCreateDTO newTransactionDTO) {

        CryptoCurrency crypto = currencyService.findBySymbolIs(newTransactionDTO.cryptoSymbol).get(0);
        User user = userService.findUser(newTransactionDTO.userId);

        Transaction transaction = transactionService.createTransaction(crypto,
                newTransactionDTO.amountOfCrypto, crypto.price, crypto.getArsPrice(),
                user, getTransactionType(newTransactionDTO.transactionType));
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
       // return ResponseEntity.status(HttpStatus.CREATED).body(TransactionDTO.from(transaction));
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
