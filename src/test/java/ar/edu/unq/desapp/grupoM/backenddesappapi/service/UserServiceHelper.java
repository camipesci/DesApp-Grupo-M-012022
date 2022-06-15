package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.UserController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceHelper {
    public UserBuilder userBuilder = new UserBuilder();
    public User user = userBuilder.build();
    public User another_user = userBuilder.withName("Another name").build();
    public User h2_user;

    @Autowired
    UserService userQueryService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userQueryController;

    @Autowired
    TransactionService transactionQueryService;

    @BeforeEach
    void setup() {
        // create user
        h2_user = userQueryService.createUser(user.name,user.lastName,user.email,user.address,user.password);
    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }
}