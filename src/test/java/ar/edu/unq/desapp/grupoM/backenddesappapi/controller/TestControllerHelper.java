package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserQueryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class TestControllerHelper {
    public UserBuilder userBuilder = new UserBuilder();
    public User user = userBuilder.build();
    public User another_user = userBuilder.withName("Another name").build();
    public User controller_user;

    @Autowired
    UserQueryService userQueryService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserQueryController userQueryController;

    @BeforeEach
    void setup() {
        // create user
       controller_user = userQueryController.createUser(user).getBody();
    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }
}