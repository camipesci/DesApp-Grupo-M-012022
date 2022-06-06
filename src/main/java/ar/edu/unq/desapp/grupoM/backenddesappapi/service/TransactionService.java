package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionrRepository;

    public List<Transaction> getTransactions() {
        return (List<Transaction>) transactionrRepository.findAll();
    }

    public Transaction findTransaction(Long id) throws Exception {
        return transactionrRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
    }

    public Transaction createTransaction(CryptoCurrency cryptoCurrency, Double cryptoAmount, Double cryptoPrice,
                                         Double cryptoArsPrice, User user, Transaction.TransactionType transactionType){

        Transaction newTransaction = new Transaction(cryptoCurrency, cryptoAmount, cryptoPrice,
                                                     cryptoArsPrice, user, transactionType);

        return transactionrRepository.save(newTransaction);
    }
}

