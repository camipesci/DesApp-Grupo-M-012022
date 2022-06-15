package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.*;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.TransactionNotFoundException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public List<Transaction> getTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByStatus(Transaction.Status status) {
        return (List<Transaction>) transactionRepository.findTransactionsByStatus(status);
    }

    public Transaction findTransaction(Long id) throws TransactionNotFoundException {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
    }

    public Transaction findTransactionByUser(User user) throws TransactionNotFoundException {
        return transactionRepository.findTransactionsByUserOrInterestedUser(user, user);

    }

    public Transaction createTransaction(CryptoCurrency cryptoCurrency, Double cryptoAmount, Double cryptoPrice,
                                         Double cryptoArsPrice, User user, Transaction.Type type){

        Transaction newTransaction = new Transaction(cryptoCurrency, cryptoAmount, cryptoPrice,
                                                     cryptoArsPrice, user, type);
        return transactionRepository.save(newTransaction);
    }

    public Transaction updateTransactionStatus(Transaction transaction, Transaction.Status status) {
        Transaction transactionToModify = transactionRepository.findById(transaction.getId()).get();
        transactionToModify.status = status;


        return transactionRepository.save(transactionToModify);
    }

    public Transaction updateTransactionInterestedUser(Transaction transaction, User interestedUser) {
        Transaction transactionToModify = transactionRepository.findById(transaction.getId()).get();
        transactionToModify.setInterestedUser(interestedUser);


        return transactionRepository.save(transactionToModify);
    }



    public void processTransaction(Transaction transaction, User interestedUser) {
        updateUserOperations(transaction.getUser());
        updateUserOperations(interestedUser);

        long difference = ChronoUnit.MINUTES.between(LocalDateTime.now(),  transaction.getDate());

        if(difference <= 30){
            updateUserScore(transaction.getUser(),10);
            updateUserScore(interestedUser,10);
        }else {
            updateUserScore(transaction.getUser(),5);
            updateUserScore(interestedUser,5);
        }
        this.updateTransactionStatus(transaction, Transaction.Status.CONFIRMED);
        this.updateTransactionInterestedUser(transaction, interestedUser);
    }

    public UserTradedVolumenDTO getTradedVolumes(DatesDTO dates, Long id) {

        User user = userService.findUser(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse(dates.getFrom(), formatter);
        LocalDateTime to = LocalDateTime.parse(dates.getTo(), formatter);

        List<Transaction> confirmed_transactions = this.transactionRepository.findTransactionsByUserOrInterestedUserAndStatusAndDateIsBetween(user, user, Transaction.Status.CONFIRMED, from, to);
        List<CryptoCurrency> list_of_cryptos = this.listOfCryptosFromTransactions(confirmed_transactions);

        return new UserTradedVolumenDTO(UserDTO.from(user), LocalDateTime.now(),CryptoDTO.from(list_of_cryptos), totalUSDVolumen(confirmed_transactions), totalARSVolumen(confirmed_transactions));
    }

    public Double totalUSDVolumen(List<Transaction> transactions) {
        Double total_usd_volumen = 0.0;
        for(Transaction transaction : transactions) {
            total_usd_volumen = total_usd_volumen + transaction.getCryptoPrice();
        }
        return total_usd_volumen;
    }

    public Double totalARSVolumen(List<Transaction> transactions) {
        Double total_ars_volumen = 0.0;
        for (Transaction transaction : transactions) {
            total_ars_volumen = total_ars_volumen + transaction.getCryptoArsPrice();
        }
        return total_ars_volumen;
    }

    public List<CryptoCurrency> listOfCryptosFromTransactions(List<Transaction> transactions) {
        List<CryptoCurrency> cryptos = new ArrayList<CryptoCurrency>();
        for (Transaction transaction : transactions)
        {
            cryptos.add(transaction.getCryptoCurrency());
        }
        return cryptos;
    }

    public void updateUserScore(User user, Integer score){
        userService.updateUserScore(user, score);
    }

    public void updateUserOperations(User user){
        userService.updateUserOperations(user);
    }


}

