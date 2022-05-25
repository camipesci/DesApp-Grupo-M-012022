package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserControllerTest extends TestControllerHelper {

    @Test
    void createUser() {
       UserDTO created_user = userController.createUser(create_user_data).getBody();

       assertEquals(created_user.name, create_user_data.name);
       assertEquals(created_user.lastName, create_user_data.lastName);
       assertEquals(created_user.email, create_user_data.email);
       assertEquals(created_user.address, create_user_data.address);

    }

    @Test
    void updateUser() throws UserNotFoundException {
        UserDTO updated_user = userController.updateUser(controller_user.id, update_user_data).getBody();
        assertEquals(updated_user.name, update_user_data.name);
    }

    @Test
    void getUsers() {
        // there is a user created in test helper and we test that it is in users list
        assertEquals(userController.getUsers().getBody().size(),1);
        assertEquals(userController.getUsers().getBody().get(0).name, controller_user.name);
    }

    @Test
    void findUser() throws UserNotFoundException {
        assertEquals(userController.findUser(controller_user.id).getBody().id, controller_user.id);
    }

    @Test
    void deleteUser() throws Exception {
        // user delete
        userController.deleteUser(controller_user.id);
        assertEquals(userController.getUsers().getBody().size(), 0);
    }
}