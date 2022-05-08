package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class MockUserGenerateService {

    @Autowired
    private UserRepository accountRepository;

    @Autowired
    private UserIdGenerationService accountIdGenerationService;

    public void generateUsers() {
        User user1=  User.builder()
                .id(accountIdGenerationService.newUserId())
                .name("Ricardo")
                .lastName("LastNameasdsad")
                .address("Av. La plata 123")
                .email("nicolas@gmail.com")
                .password("Marta123@")
                .cvu("1234567891234567891012")
                .wallet(88888688)
                .build();
        accountRepository.save(user1);

        User user2=  User.builder()
                .id(accountIdGenerationService.newUserId())
                .name("Martita")
                .lastName("LastNameasdasd")
                .address("Av. La plata 321")
                .email("nicolas@gmail.com")
                .password("Marta123@")
                .cvu("1234567891234567891012")
                .wallet(88888888)
                .build();

        accountRepository.save(user2);
    }
}
