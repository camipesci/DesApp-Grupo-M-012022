package ar.edu.unq.desapp.grupoM.backenddesappapi.service;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest extends TestServiceHelper {

    @Test
    void createUser() throws Exception {
        // adds a new user
       User created_user = userQueryService.createUser(user.name,user.lastName,user.email,user.address,user.password);

        // check creation
        assertEquals(userQueryService.findUser(created_user.getId()).name, created_user.name);

        // check user id generation
        assertEquals(userQueryService.findUser(created_user.getId()).getId(), created_user.getId());
    }

    @Test
    void updateUser() throws Exception {
        User updated_user = userQueryService.updateUser(h2_user.getId(), another_user.name,another_user.lastName,another_user.email,another_user.address,another_user.password,another_user.cvu, another_user.wallet);

        assertEquals(userQueryService.findUser(updated_user.getId()).name,updated_user.name);
        assertEquals(userQueryService.getUsers().size(), 1);
    }

    @Test
    void getUsers() {
        assertEquals(userQueryService.getUsers().get(0).name, h2_user.name);
        assertEquals(userQueryService.getUsers().size(), 1);
    }

    @Test
    void findUser() throws Exception {
        assertEquals(userQueryService.findUser(h2_user.getId()).name, h2_user.name);
        assertEquals(userQueryService.getUsers().size(), 1);
    }

    @Test
    void deleteUser() {
        userQueryService.deleteUser(h2_user.getId());
        // check user existence
        assertEquals(userQueryService.getUsers().size(), 0);
    }

}