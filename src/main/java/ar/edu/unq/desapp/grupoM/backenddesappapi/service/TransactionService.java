package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.*;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.TransactionNotFoundException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CryptoService cryptoService;




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

    public Transaction createTransaction(TransactionCreateDTO transactionCreateDTO) throws IOException {

        Crypto crypto = cryptoService.findCryptoOrCallBinanceAPI(transactionCreateDTO.cryptoSymbol).get(0);
        User user = userService.findUser(transactionCreateDTO.userId);
        Transaction transaction = null;

        if(cryptoHasMarginValuePrice(transactionCreateDTO.getCryptoPrice(), crypto.getPrice()) ){
            transaction = new Transaction(crypto,
                    transactionCreateDTO.getAmountOfCrypto(), crypto.getPrice(), crypto.getArsPrice(),
                    user, getTransactionType(transactionCreateDTO.getTransactionType()));

        }else {
            transaction = new Transaction(crypto,
                    transactionCreateDTO.getAmountOfCrypto(), crypto.getPrice(), crypto.getArsPrice(),
                    user, getTransactionType(transactionCreateDTO.getTransactionType()));
            transactionRepository.save(transaction);
            this.updateTransactionStatus(transaction, Transaction.Status.CANCELED);
        }

        return transactionRepository.save(transaction);
    }
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


    public Boolean cryptoHasMarginValuePrice(Double user_price, Double crypto_price){
        Double result = 0.0;
        result = 100 * ((user_price - crypto_price) / crypto_price);
        Double positive_result = Math.abs(result);

        return positive_result < 5;
    }

    public Transaction updateTransactionStatus(Transaction transaction, Transaction.Status status) {
        Transaction transactionToModify = transactionRepository.findById(transaction.getId()).get();
        transactionToModify.status = status;


        return transactionRepository.save(transactionToModify);
    }

    public Transaction updateTransactionInterestedUser(Transaction transaction, User interestedUser) {
        Transaction transactionToModify = transactionRepository.findById(transaction.getId()).get();
        User user = userService.findUser(interestedUser.getUser_id());
        transactionToModify.setInterestedUser(user);


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
        //TODO: Crear transaccion opuesta para el interested_user
    }

    public UserTradedVolumenDTO getTradedVolumes(DatesDTO dates, Long user_id) {

        User user = userService.findUser(user_id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse(dates.getFrom(), formatter);
        LocalDateTime to = LocalDateTime.parse(dates.getTo(), formatter);

        List<Transaction> confirmed_transactions = this.transactionRepository.findTransactionsByUserOrInterestedUserAndStatusAndDateIsBetween(user, user, Transaction.Status.CONFIRMED, from, to);
        List<Crypto> list_of_cryptos = this.listOfCryptosFromTransactions(confirmed_transactions);

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

    public List<Crypto> listOfCryptosFromTransactions(List<Transaction> transactions) {
        List<Crypto> cryptos = new ArrayList<Crypto>();
        for (Transaction transaction : transactions)
        {
            cryptos.add(transaction.getCrypto());
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

