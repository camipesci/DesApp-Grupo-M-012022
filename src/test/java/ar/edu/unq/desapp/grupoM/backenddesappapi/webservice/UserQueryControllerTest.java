package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserQueryControllerTest extends TestControllerHelper {

    @Test
    void createUser() throws Exception {
       User created_user = userQueryController.createUser(user).getBody();
       assertEquals(userQueryController.findUser(created_user.id).getBody().name, created_user.name);
    }

    @Test
    void updateUser() throws Exception {
        // user delete
        User updated_user = userQueryController.updateUser(controller_user.id, another_user).getBody();
        assertEquals(userQueryController.findUser(updated_user.id).getBody().name, updated_user.name);
    }

    @Test
    void getUsers() {
        assertEquals(userQueryController.getUsers().getBody().get(0).name, controller_user.name);
    }

    @Test
    void findUser() throws Exception {
        assertEquals(userQueryController.findUser(controller_user.id).getBody().id, controller_user.id);
    }

    @Test
    void deleteUser() throws Exception {
        // user delete
        userQueryController.deleteUser(controller_user.id);
        assertEquals(userQueryController.getUsers().getBody().size(), 0);
    }
}