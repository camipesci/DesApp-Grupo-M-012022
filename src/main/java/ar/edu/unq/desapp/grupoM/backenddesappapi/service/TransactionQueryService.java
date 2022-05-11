package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionrRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionQueryService {

    @Autowired
    private TransactionrRepository transactionrRepository;

    public List<Transaction> getTransactions() {
        return (List<Transaction>) transactionrRepository.findAll();
    }

    public Transaction findTransaction(Long id) throws Exception {
        return transactionrRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
    }
}

