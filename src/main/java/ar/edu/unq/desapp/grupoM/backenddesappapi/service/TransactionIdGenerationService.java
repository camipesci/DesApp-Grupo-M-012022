package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionIdGenerationService {
    public Long newTransactionId() {
        return System.nanoTime();
    }
}
