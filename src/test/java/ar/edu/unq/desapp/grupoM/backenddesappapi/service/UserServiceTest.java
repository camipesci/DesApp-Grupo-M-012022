package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    UserService userService = new UserService();
    UserBuilder userBuilder = new UserBuilder();
    User user = userBuilder.build();
    User user_with_wallet = UserBuilder.user().withWallet(12345678).build();


    @Test
    public void createUserTest() {
        // create user
        userService.createUser(user);

        // check existence
        assertEquals(userService.allUsers().size(), 1);

    }

    @Test
    public void getUserTest() {
        // create user
        userService.createUser(user_with_wallet);

        // get created user
        assertEquals(userService.getUser(user_with_wallet.getWallet()), user_with_wallet);

    }

    @Test
    public void deleteUserTest() {
        // create user and check existence
        userService.createUser(user_with_wallet);
        assertEquals(userService.allUsers().size(), 1);

        // delete user and check non-existence
        userService.deleteUser(user_with_wallet.getWallet());
        assertEquals(userService.allUsers().size(), 0);

    }

    @Test
    public void updateUserTest() {
        // setup users variables
        User userToModify = user_with_wallet;
        User updateParams = user;

        // build users
        userService.createUser(userToModify);
        userService.createUser(updateParams);

        // update user
        userService.updateUser(userToModify.getWallet(),updateParams);

        // get updated user
        User updatedUser = userService.getUser(userToModify.getWallet());

        // assert updated values
        assertEquals(updatedUser.getWallet(), updateParams.getWallet());
    }
}
