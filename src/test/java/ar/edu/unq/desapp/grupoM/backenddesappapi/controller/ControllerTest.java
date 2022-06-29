package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.*;
import ar.edu.unq.desapp.grupoM.backenddesappapi.external_api.BinanceAPI;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.CryptoRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.assertj.core.api.AbstractBigIntegerAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest{

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CryptoRepository cryptoRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserController userController;

    @Autowired
    CryptoController cryptoController;

    @LocalServerPort
    protected int port;

    public UserBuilder userBuilder = new UserBuilder();
    public User create_dto = userBuilder.build();
    public User update_dto = userBuilder.withName("Another name").build();
    public UserCreateDTO create_user_data = new UserCreateDTO(create_dto.getName(), create_dto.getLastName(), create_dto.getEmail(), create_dto.getAddress(), create_dto.getPassword());
    public UserCreateDTO update_user_data = new UserCreateDTO(update_dto.getName(), update_dto.getLastName(), update_dto.getEmail(), update_dto.getAddress(), update_dto.getPassword());
    public UserDTO controller_user;
    public CryptoDTO crypto;

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        cryptoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @BeforeEach
    void setup() {
        // create user
        controller_user = userController.createUser(create_user_data).getBody();
        crypto = cryptoController.getCryptoPrice("ALICEUSDT").getBody();
    }


    protected String baseURL() {

        return "http://localhost:"+ port +"/api";
    }

    protected String usersURL() {

        return baseURL() + "/users";
    }

    protected String userURLWithId(Long userId) {

        return usersURL() + "/" + userId;
    }

    protected String transactionURL() {

        return baseURL() + "/transactions";
    }

    protected String transactionURLWithId(Long transactionId) {

        return transactionURL() + "/transaction" + transactionId ;
    }

    protected String transactionURLWithtransactionIduserId(Long transactionId, Long userId) {

        return transactionURL() + "/transaction" + transactionId +"/process/users/interested_user" + userId ;
    }

    protected String transactionURLWithProcessTransaction(Long Id) {

        return transactionURL() + "/users" + Id + "/traded_volumes";
    }

    protected UserCreateDTO createUserDTO() {

        return new UserCreateDTO("Marta", "Fort", "marta@gmail.com", "Av. la plata 123", "Martita123@");
    }

    protected UserDTO createUser(UserCreateDTO userCreationDTO) {

        return restTemplate.postForObject(usersURL(), new HttpEntity(userCreationDTO), UserDTO.class);
    }

    protected UserDTO findUserById(Long userId) {

        return restTemplate.getForObject(userURLWithId(userId), UserDTO.class);
    }

    protected TransactionCreateDTO createTransactionDTO() {

        return new TransactionCreateDTO("ADAUSDT", 1.0, 120.10, "PURCHASE", 1L);

    }

    protected TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO) {

        return restTemplate.postForObject(transactionURL(), new HttpEntity(transactionCreateDTO), TransactionDTO.class);

    }

    protected TransactionDTO findTransactionById(Long transactionId) {

        return restTemplate.getForObject(transactionURLWithId(transactionId), TransactionDTO.class);
    }




}


