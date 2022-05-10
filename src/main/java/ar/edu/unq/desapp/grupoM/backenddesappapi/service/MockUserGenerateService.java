package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class MockUserGenerateService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private UserIdGenerationService userIdGenerationService;

    public void generateUsers() {
        User user1=  User.builder()
                .id(userIdGenerationService.newUserId())
                .name("Ricardo")
                .lastName("Fort")
                .address("Av. La plata 123")
                .email("ricardo@gmail.com")
                .password("Ricardo123@")
                .cvu("1234567891234567891012")
                .wallet(11111111)
                .build();
        userRepository.save(user1);

        User user2=  User.builder()
                .id(userIdGenerationService.newUserId())
                .name("Martita")
                .lastName("Fort")
                .address("Av. La plata 321")
                .email("marta@gmail.com")
                .password("Marta123@")
                .cvu("1234567891234567891013")
                .wallet(88888888)
                .build();

        userRepository.save(user2);
    }
}
