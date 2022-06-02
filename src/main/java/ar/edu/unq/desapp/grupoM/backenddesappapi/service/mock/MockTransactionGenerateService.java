package ar.edu.unq.desapp.grupoM.backenddesappapi.service.mock;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionrRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.TransactionIdGenerationService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserIdGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MockTransactionGenerateService {
    @Autowired
    private TransactionrRepository transactionrRepository ;

    @Autowired
    private TransactionIdGenerationService transactionIdGenerationService;

    @Autowired
    public UserRepository userRepository ;

    @Autowired
    public UserIdGenerationService userIdGenerationService;


    public void generateTransaction() {
        User user1=  User.builder()
                .id(userIdGenerationService.newUserId())
                .name("Ricardo")
                .lastName("Fort")
                .address("Av. La plata 123")
                .email("ricardo@gmail.com")
                .password("Ricardo123@")
                .cvu("1234567891234567891012")
                .wallet(11111111)
                .build();
        userRepository.save(user1);
        Transaction transaction1=  Transaction.builder()
                .id (transactionIdGenerationService.newTransactionId())
                .cryptoCurrency("USD")
                .cryptoAmount  (1.0)
                .cryptoPrice (2.0)
                .cryptoArsPrice (1000.0)
                .userid (user1.id)
                .date (new Date())
                .transactionType (Transaction.TransactionType.VENTA)
                .build();
        transactionrRepository.save(transaction1);

        Transaction transaction2=  Transaction.builder()
                .id (transactionIdGenerationService.newTransactionId())
                .cryptoCurrency("USD")
                .cryptoAmount  (1.0)
                .cryptoPrice (2.0)
                .cryptoArsPrice (1000.0)
                .userid (user1.id)
                .date (new Date())
                .transactionType (Transaction.TransactionType.COMPRA)
                .build();
        transactionrRepository.save(transaction2);

    }
}
