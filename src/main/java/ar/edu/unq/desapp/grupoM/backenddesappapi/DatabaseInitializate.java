package ar.edu.unq.desapp.grupoM.backenddesappapi;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.CryptoBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.TransactionBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CryptoService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializate {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;
    @Autowired
    private CryptoService cryptoService;

    public UserBuilder userBuilder = new UserBuilder();
    public CryptoBuilder cryptoBuilder = new CryptoBuilder();
    public TransactionBuilder transactionBuilder = new TransactionBuilder();


    private static Logger log = LoggerFactory.getLogger(DatabaseInitializate.class);

    @PostConstruct
    public void initializer() throws Exception {

        log.info("---------------- Seeding database start ----------------");

        // User Data

        User user1_data = userBuilder.build();
        User user_2_data = userBuilder.withName("Felipe").build();
        User user1 = userService.createUser(user1_data.getName(), user1_data.getLastName(), user1_data.getEmail(), user1_data.getAddress(), user1_data.getPassword());
        User user2 = userService.createUser(user_2_data.getName(), user_2_data.getLastName(), user_2_data.getEmail(), user_2_data.getAddress(), user_2_data.getPassword());

        // Crypto data

        Crypto crypto_1_data = cryptoBuilder.build();
        Crypto crypto_2_data = cryptoBuilder.withNameCrypto("ALICEUSDT").build();
        Crypto crypto = cryptoService.createCrypto(crypto_1_data.getSymbol(), crypto_1_data.getPrice());
        Crypto crypto2 =  cryptoService.createCrypto(crypto_2_data.getSymbol(), crypto_2_data.getPrice());

        // Transaction data
        Transaction transaction = transactionBuilder.build();
        transaction.setUser(user1);
        transaction.setCrypto(crypto);
        Transaction transaction2 = transactionBuilder.build();
        transaction2.setUser(user2);
        transaction2.setCrypto(crypto2);
        transactionService.createTransaction(TransactionCreateDTO.from(transaction));
        transactionService.createTransaction(TransactionCreateDTO.from(transaction2));

        log.info("---------------- Seeding finished  ----------------");
    }
}
