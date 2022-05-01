package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    UserService userService = new UserService();
    UserBuilder userBuilder = new UserBuilder();

    @Test
    public void createUserTest() {

        User user = userBuilder.build();
        userService.createUser(user);

        assertEquals(userService.allUsers().size(), 1);

    }

    @Test
    public void getUserTest() {

        User user = UserBuilder.user().withWallet(12345678).build();
        userService.createUser(user);

        assertEquals(userService.getUser(user.getWallet()), user);

    }

    @Test
    public void deleteUserTest() {

        User user = UserBuilder.user().withWallet(12345678).build();
        userService.createUser(user);

        assertEquals(userService.allUsers().size(), 1);
        userService.deleteUser(user.getWallet());
        assertEquals(userService.allUsers().size(), 0);

    }

    @Test
    public void updateUserTest() {

        User oldUser = UserBuilder.user().withWallet(87654321).build();
        User userUpdate = userBuilder.build();
        userService.createUser(oldUser);
        userService.createUser(userUpdate);

        userService.updateUser(oldUser.getWallet(),userUpdate);

        assertEquals(userService.getUser(userUpdate.getWallet()).getWallet(), 12345678 );




    }




}
