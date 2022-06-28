package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.UserCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class TestControllerHelper {
    public UserBuilder userBuilder = new UserBuilder();
    public User create_dto = userBuilder.build();
    public User update_dto = userBuilder.withName("Another name").build();
    public UserCreateDTO create_user_data = new UserCreateDTO(create_dto.getName(), create_dto.getLastName(), create_dto.getEmail(), create_dto.getAddress(), create_dto.getPassword());
    public UserCreateDTO update_user_data = new UserCreateDTO(update_dto.getName(), update_dto.getLastName(), update_dto.getEmail(), update_dto.getAddress(), update_dto.getPassword());
    public UserDTO controller_user;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

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