package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MockTransactionGenerateService {
    @Autowired
    private TransactionrRepository transactionrRepository ;

    @Autowired
    private TransactionIdGenerationService transactionIdGenerationService;

    public void generateTransaction() {
        Transaction transaction1=  Transaction.builder()
                .cryptoCurrency("USD")
                .cryptoAmount  (1.0)
                .cryptoPrice (2.0)
                .cryptoArsPrice (1000.0)
                .userid (transactionIdGenerationService.newTransactionId())
                .date (new Date())
                .transactionType ("COMPRA")
                .build();
        transactionrRepository.save(transaction1);

        Transaction transaction2=  Transaction.builder()
                .cryptoCurrency("USD")
                .cryptoAmount  (1.0)
                .cryptoPrice (2.0)
                .cryptoArsPrice (1000.0)
                .userid (transactionIdGenerationService.newTransactionId())
                .date (new Date())
                .transactionType ("COMPRA")
                .build();
        transactionrRepository.save(transaction2);

    }
}
