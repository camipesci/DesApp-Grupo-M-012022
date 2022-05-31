package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.CryptoRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class TestControllerHelper {
    public UserBuilder userBuilder = new UserBuilder();
    public User create_user_data = userBuilder.build();
    public User update_user_data = userBuilder.withName("Another name").build();
    public UserDTO controller_user;

    @Autowired
    UserService userService;


    @Autowired
    UserRepository userRepository;


    @Autowired
    UserController userController;

    @BeforeEach
    void setup() {
        // create user
       controller_user = userController.createUser(create_user_data).getBody();

    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }
}