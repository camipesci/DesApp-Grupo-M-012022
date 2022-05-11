package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.TransactionBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserQueryService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.webservice.MockTransactionController;
import ar.edu.unq.desapp.grupoM.backenddesappapi.webservice.UserController;
import ar.edu.unq.desapp.grupoM.backenddesappapi.webservice.UserQueryController;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class TestServiceHelper {
    public UserBuilder userBuilder = new UserBuilder();
    public User user = userBuilder.build();
    public User another_user = userBuilder.withName("Another name").build();
    public User h2_user;
    public TransactionBuilder  transactionBuilder = new TransactionBuilder();
    public Transaction transaction = transactionBuilder.build() ;
    public Transaction h2_transaction ;

    @Autowired
    UserQueryService userQueryService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserQueryController userQueryController;

    @Autowired
    TransactionQueryService transactionQueryService;

    @Autowired
    MockTransactionController mockTransactionController;

    @BeforeEach
    void setup() {
        // create user
        h2_user = userQueryService.createUser(user.name,user.lastName,user.email,user.address,user.password
                                              ,user.cvu, user.wallet);
        // create transaction
        h2_transaction = transactionQueryService.createTransaction(transaction.getCryptoCurrency(),
                                                                   transaction.getCryptoAmount(),
                                                                   transaction.getCryptoPrice(),
                                                                   transaction.getCryptoArsPrice(),
                                                                   transaction.getUserId(),
                                                                   transaction.getDate(),
                                                                   transaction.gettransactionType());
    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }
}