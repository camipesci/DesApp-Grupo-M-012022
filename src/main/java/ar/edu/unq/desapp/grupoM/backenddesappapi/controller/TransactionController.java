package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.aspect.LogExecutionTime;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.*;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.InvalidTransactionException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.InvalidUserException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.TransactionNotFoundException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Api(tags = "Transaction Controller")
@Tag(name = "Transaction Controller", description = "Manage Transaction ABM")
@RestController
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;


    @Operation(summary = "Creates a new transaction")
    @PostMapping("/api/transactions")
    @ResponseBody
    @Transactional
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionCreateDTO transactionCreateDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionDTO.from(transactionService.createTransaction(transactionCreateDTO)));

    }

    @Operation(summary = "Get all active transactions")
    @GetMapping("/api/transactions/active")
    @Transactional
    public ResponseEntity<List<TransactionDTO>> getTransactionsActive() {
        List<Transaction> transactions = transactionService.getTransactionsByStatus(Transaction.Status.PENDING);
        return ResponseEntity.ok().body(TransactionDTO.from(transactions));
    }

    @Operation(summary = "Get all  transactions")
    @GetMapping("/api/transactions")
    @Transactional
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<Transaction> transactions = transactionService.getTransactions();
        return ResponseEntity.ok().body(TransactionDTO.from(transactions));
    }

    @Operation(summary = "Finds a transaction by its id")
    @GetMapping("/api/transactions/{transaction_id}")
    @Transactional
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long transaction_id) throws TransactionNotFoundException {
        Transaction transaction = transactionService.findTransaction(transaction_id);
        return ResponseEntity.ok().body(TransactionDTO.from(transaction));
    }

    @Operation(summary = "Find user transactions by its id")
    @GetMapping("/api/transactions/users/{user_id}")
    @Transactional
    public ResponseEntity<TransactionDTO> getTransactionByUser(@PathVariable Long user_id) throws TransactionNotFoundException {
        User user = userService.findUser(user_id);
        Transaction transaction = transactionService.findTransactionByUser(user);
        return ResponseEntity.ok().body(TransactionDTO.from(transaction));
    }

    @Operation(summary = "Proccess a transaction")
    @PutMapping("/api/transactions/transaction={transaction_id}/process/users/interested_user={interested_user_id}")
    @Transactional
    public ResponseEntity<ProcessedTransactionDTO> processTransaction(@PathVariable Long transaction_id, @PathVariable Long interested_user_id) throws TransactionNotFoundException, IOException {

        Transaction transaction = transactionService.findTransaction(transaction_id);
        User interested_user = userService.findUser(interested_user_id);

        validateUserAndTransaction(interested_user_id, transaction );

        return ResponseEntity.ok().body(transactionService.processTransaction(transaction, interested_user));

    }

    @Operation(summary = "Gets a user trade volumen giving a user_id")
    @PostMapping("/api/transactions/users/{user_id}/traded_volumes")
    @Transactional
    public ResponseEntity<UserTradedVolumenDTO> getTradedVolume(@PathVariable Long user_id, @RequestBody DatesDTO dates){
        return ResponseEntity.ok().body(transactionService.getTradedVolumes(dates,user_id));
    }

    //Aux method
    public void validateUserAndTransaction(Long interested_user_id, Transaction transaction ){
        if(interested_user_id == transaction.getUser().getUser_id()){
            throw new InvalidUserException(interested_user_id);
        }else if(transaction.getStatus() == Transaction.Status.CONFIRMED) {
            throw new InvalidTransactionException(transaction.getId());
        }
    }




}
