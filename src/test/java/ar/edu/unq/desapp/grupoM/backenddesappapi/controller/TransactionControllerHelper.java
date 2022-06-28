package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.TransactionBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.*;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class TransactionControllerHelper {
    public UserBuilder userBuilder = new UserBuilder();
    public User create_user_data = userBuilder.build();
    public User update_user_data = userBuilder.withName("Another name").build();
    public UserDTO controller_user;
    public CryptoDTO crypto;

    public TransactionDTO controller_transaction;
    public TransactionBuilder transactionBuilder = new TransactionBuilder();
    public TransactionCreateDTO create_transaction_data = TransactionCreateDTO.from(transactionBuilder.build());

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserController userController;

    @Autowired
    CryptoController cryptoController;

    @Autowired
    TransactionController transactionController;

    @BeforeEach
    void setup() throws IOException {
        // create user
        UserCreateDTO userCreateDTO = new UserCreateDTO(create_user_data.getName(), create_user_data.getLastName(), create_user_data.getEmail(), create_user_data.getAddress(), create_user_data.getPassword());
       controller_user = userController.createUser(userCreateDTO).getBody();
       crypto = cryptoController.getCryptoPrice("ALICEUSDT").getBody();
       //create transaction
        controller_transaction = transactionController.createTransaction(create_transaction_data).getBody();
    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        transactionRepository.deleteAll();

    }
}