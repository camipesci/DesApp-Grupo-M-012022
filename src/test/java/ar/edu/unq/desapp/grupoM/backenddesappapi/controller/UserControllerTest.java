package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.UserCreateDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest extends ControllerTest{

    @Test
    void createUserSuccessfully() {
        UserCreateDTO userCreateDTO = createUserDTO();
        UserDTO response = createUser(userCreateDTO);

        assertThat(response.getName()).isEqualTo(userCreateDTO.getName());
        assertThat(response.getAddress()).isEqualTo(userCreateDTO.getAddress());
        assertThat(response.getLastName()).isEqualTo(userCreateDTO.getLastName());
        assertThat(response.getEmail()).isEqualTo(userCreateDTO.getEmail());
    }

    @Test
    void findUserByIdSuccessfully() {
        UserCreateDTO userCreateDTO = createUserDTO();
        UserDTO userCreateResponse = createUser(userCreateDTO);

        UserDTO response = findUserById(userCreateResponse.id);

        assertThat(response.getName()).isEqualTo(userCreateDTO.getName());
        assertThat(response.getAddress()).isEqualTo(userCreateDTO.getAddress());
        assertThat(response.getLastName()).isEqualTo(userCreateDTO.getLastName());
        assertThat(response.getEmail()).isEqualTo(userCreateDTO.getEmail());

    }


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
        assertEquals(userController.getUsers().getBody().get(0).name, controller_user.name);
    }

    @Test
    void findUser() throws UserNotFoundException {
        assertEquals(userController.findUser(controller_user.id).getBody().id, controller_user.id);
    }

    @Test
    void deleteUser() throws Exception {
        // user delete
        Integer initial_amount_of_users = userController.getUsers().getBody().size();
        userController.deleteUser(controller_user.id);


        assertEquals(userController.getUsers().getBody().size(), initial_amount_of_users - 1);
    }


}