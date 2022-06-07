package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public List<Transaction> getTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public Transaction findTransaction(Long id) throws Exception {
        return transactionRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
    }

    public Transaction createTransaction(CryptoCurrency cryptoCurrency, Double cryptoAmount, Double cryptoPrice,
                                         Double cryptoArsPrice, User user, Transaction.Type type){

        Transaction newTransaction = new Transaction(cryptoCurrency, cryptoAmount, cryptoPrice,
                                                     cryptoArsPrice, user, type);

        return transactionRepository.save(newTransaction);
    }

    public Transaction updateTransaction(Transaction transaction, Transaction.Status status) {
        Transaction transactionToModify = transactionRepository.findById(transaction.getId()).get();
        transactionToModify.status = status;


        return transactionRepository.save(transactionToModify);
    }

    public void processTransaction(Transaction transaction, User another_user) {
        updateUserOperations(transaction.getUser());
        updateUserOperations(another_user);

        long difference = ChronoUnit.MINUTES.between(LocalDateTime.now(),  transaction.getDate());

        if(difference <= 30){
            updateUserScore(transaction.getUser(),10);
            updateUserScore(another_user,10);
        }else {
            updateUserScore(transaction.getUser(),5);
            updateUserScore(another_user,5);
        }
        transaction.setStatus(Transaction.Status.CONFIRMED);
    }

    public void updateUserScore(User user, Integer score){
        userService.updateUserScore(user, score);
    }

    public void updateUserOperations(User user){
        userService.updateUserOperations(user);
    }
}

