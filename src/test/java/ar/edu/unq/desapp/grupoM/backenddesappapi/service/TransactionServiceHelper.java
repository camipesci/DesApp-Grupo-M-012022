package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.TransactionBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.UserController;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class TransactionServiceHelper {
    public UserBuilder userBuilder = new UserBuilder();
    public User user = userBuilder.build();
    public User h2_user;
    public TransactionBuilder transactionBuilder = new TransactionBuilder();
    public Transaction transaction = transactionBuilder.build() ;
    public Transaction h2_transaction ;

    @Autowired
    UserService userQueryService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRespository;

    @Autowired
    UserController userQueryController;

    @Autowired
    TransactionService transactionQueryService;

    @BeforeEach
    void setup() {
        // create user
        h2_user = userQueryService.createUser(user.name,user.lastName,user.email,user.address,user.password);
        // create transaction
        h2_transaction = transactionQueryService.createTransaction(transaction.getCryptoCurrency(),
                                                                   transaction.getCryptoAmount(),
                                                                   transaction.getCryptoPrice(),
                                                                   transaction.getCryptoArsPrice(),
                                                                   transaction.getUser(),
                                                                   transaction.getType());
    }


    @AfterEach
    void tearDown() {
        transactionRespository.deleteAll();
    }
}