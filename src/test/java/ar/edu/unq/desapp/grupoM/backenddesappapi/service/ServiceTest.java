package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.CryptoBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.TransactionBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.UserController;
import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.CryptoRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.TransactionRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;


class ServiceTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CryptoRepository cryptoRepository;

    @Autowired
    UserService userService;

    @Autowired
    CryptoService cryptoService;

    public UserBuilder userBuilder = new UserBuilder();
    public User user = userBuilder.build();
    public User another_user = userBuilder.withName("Another name").build();
    public User h2_user;

    public TransactionBuilder transactionBuilder = new TransactionBuilder();
    public Transaction transaction = null;
    public Transaction h2_transaction ;

    public CryptoBuilder cryptoBuilder = new CryptoBuilder();
    public Crypto crypto = cryptoBuilder.build();
    public Crypto h2_crypto;


    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        userRepository.deleteAll();
        cryptoRepository.deleteAll();
    }

    @BeforeEach
    void setup() throws IOException {
        // create user
        h2_user = userService.createUser(user.name,user.lastName,user.email,user.address,user.password);
        // create crypto
        h2_crypto = cryptoService.createCrypto(crypto.getSymbol(), crypto.getPrice());
        transaction = transactionBuilder.withUserAndCrypto(h2_user, h2_crypto).build() ;
        // create transaction
        h2_transaction = transactionService.createTransaction(TransactionCreateDTO.from(transaction));
    }

    public User createdUserWithEmail(String email) {
        return userService.createUser(user.name,user.lastName,email,user.address,user.password);
    }

    public User createdUserWithname(String name) {
        return userService.createUser(name,user.lastName,user.email,user.address,user.password);
    }

    public User createdUserWithAdress(String adddress) {
        return userService.createUser(user.name,user.lastName,user.email,adddress,user.password);
    }

    public User createdUserWithLastName(String lastName) {
        return userService.createUser(user.name,lastName,user.email,user.address,user.password);
    }

    public User createdUserWithPassword(String password) {
        return userService.createUser(user.name,user.lastName,user.email,user.address,password);
    }

    public Transaction getTransaction(Long id) {
        return transactionService.findTransaction(id);
    }




}