/*package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.TransactionBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class TransactionControllerHelper {
    public UserBuilder userBuilder = new UserBuilder();
    public User create_user_data = userBuilder.build();
    public User update_user_data = userBuilder.withName("Another name").build();
    public UserDTO controller_user;
    public CryptoDTO crypto;

    public TransactionDTO controller_transaction;
    public TransactionBuilder transactionBuilder = new TransactionBuilder();
    public TransactionCreateDTO create_transaction_data = transactionBuilder.build();

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserController userController;

    @Autowired
    BinanceController binanceController;

    @Autowired
    TransactionController transactionController;

    @BeforeEach
    void setup() {
        // create user
       controller_user = userController.createUser(create_user_data).getBody();
       crypto = binanceController.getCryptoPrice("ALICEUSDT").getBody();
       //create transaction
        controller_transaction = transactionController.createTransaction(create_transaction_data).getBody();
    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        transactionRepository.deleteAll();

    }
}*/