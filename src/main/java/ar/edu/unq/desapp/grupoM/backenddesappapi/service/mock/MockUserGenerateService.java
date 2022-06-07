package ar.edu.unq.desapp.grupoM.backenddesappapi.service.mock;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserIdGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockUserGenerateService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private UserIdGenerationService userIdGenerationService;

    public MockUserGenerateService generateUsers() {
        User user1=  User.builder()
                .user_id(userIdGenerationService.newUserId())
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
                .user_id(userIdGenerationService.newUserId())
                .name("Martita")
                .lastName("Fort")
                .address("Av. La plata 321")
                .email("marta@gmail.com")
                .password("Marta123@")
                .cvu("1234567891234567891013")
                .wallet(88888888)
                .build();

        userRepository.save(user2);
        return null;
    }
}
