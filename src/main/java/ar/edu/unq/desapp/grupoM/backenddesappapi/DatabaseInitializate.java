package ar.edu.unq.desapp.grupoM.backenddesappapi;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.CryptoBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.TransactionBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.CryptoService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        log.info("---------------- Setting up users  ----------------");

        // User Data

        User user1_data = userBuilder.build();
        User user_2_data = userBuilder.withName("Felipe").build();
        User user1 = userService.createUser(user1_data.getName(), user1_data.getLastName(), user1_data.getEmail(), user1_data.getAddress(), user1_data.getPassword());
        User user2 = userService.createUser(user_2_data.getName(), user_2_data.getLastName(), user_2_data.getEmail(), user_2_data.getAddress(), user_2_data.getPassword());


        // Crypto data

        log.info("---------------- Setting up cryptos  ----------------");
        CryptoCurrency crypto_1_data = cryptoBuilder.build();
        CryptoCurrency crypto_2_data = cryptoBuilder.withNameCrypto("ALICEUSDT").build();
        CryptoCurrency crypto = cryptoService.createCrypto(crypto_1_data.getSymbol(), crypto_1_data.getPrice());
        CryptoCurrency crypto2 =  cryptoService.createCrypto(crypto_2_data.getSymbol(), crypto_2_data.getPrice());


        log.info("---------------- Setting up transactions  ----------------");

        // Transaction data
        CryptoCurrency crypto_for_tr1 = cryptoBuilder.withNameCrypto("ATOMUSDT").build();
        CryptoCurrency crypto_for_tr2 = cryptoBuilder.withNameCrypto("AUDIOUSDT").build();
        User user_for_tr_1 = userBuilder.withName("Camila").build();
        User user_for_tr_2 = userBuilder.withName("Nicolas").build();
        transactionService.createTransaction(crypto_for_tr1, 5.0, 2.5, crypto_1_data.getArsPrice(), user_for_tr_1, Transaction.Type.PURCHASE);
        transactionService.createTransaction(crypto_for_tr2, 5.0, 2.5, crypto_2_data.getArsPrice(), user_for_tr_2, Transaction.Type.SALE);

        log.info("---------------- Seeding finished  ----------------");
    }
}
