package ar.edu.unq.desapp.grupoM.backenddesappapi.service;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.InvalidEmailException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest extends ServiceTest {

    @Test
    void createUser() throws Exception {
        // adds a new user
       User created_user = userService.createUser(user.name,user.lastName,user.email,user.address,user.password);

        // check creation
        assertEquals(userService.findUser(created_user.getId()).name, created_user.name);

        // check user id generation
        assertEquals(userService.findUser(created_user.getId()).getId(), created_user.getId());
    }

    @Test
    void updateUser() throws Exception {
        User updated_user = userService.updateUser(h2_user.getId(), another_user.name,another_user.lastName,another_user.email,another_user.address,another_user.password);

        assertEquals(userService.findUser(updated_user.getId()).name,updated_user.name);
    }

    @Test
    void getUsers() {
        assertEquals(userService.getUsers().get(0).name, h2_user.name);
        assertEquals(userService.getUsers().size(), 1);
    }

    @Test
    void findUser() throws Exception {
        assertEquals(userService.findUser(h2_user.getId()).name, h2_user.name);
    }

    @Test
    void deleteUser() {
        userService.deleteUser(h2_user.getId());
        // check user existence
        assertEquals(userService.getUsers().size(), 0);
    }

    @Test
    void aUserInvalidEmail() {
        String invalidEmail = "mailWithOutAt";

        Exception myException = null;
        try{
            createdUserWithEmail(invalidEmail);
        }catch(Exception e){
            myException = e;
        }
        assertEquals(myException.getMessage(), "Specified email "+ invalidEmail +" format is invalid." );

    }

    @Test
    void aUserInvalidNameCannotHaveMoreThan30Letters() {
        String invalidName = "thisIsANameThanHaveMoreThan30Letters";

        Exception myException = null;
        try{
            createdUserWithname(invalidName);
        }catch(Exception e){
            myException = e;
        }
        assertEquals(myException.getMessage(), "Specified name "+ invalidName +" is invalid." );

    }

    @Test
    void aUserInvalidAddressCannotHaveLessThan10Letters() {
        String invalidAddress = "shortName";

        Exception myException = null;
        try{
            createdUserWithAdress(invalidAddress);
        }catch(Exception e){
            myException = e;
        }
        assertEquals(myException.getMessage(), "Specified address "+ invalidAddress +" is invalid." );

    }

    @Test
    void aUserInvalidLastNameCannotHaveLessThan3Letters() {
        String invalidLastName = "Ab";

        Exception myException = null;
        try{
            createdUserWithLastName(invalidLastName);
        }catch(Exception e){
            myException = e;
        }
        assertEquals(myException.getMessage(), "Specified last name "+ invalidLastName +" is invalid." );

    }

    @Test
    void aUserInvalidPassword() {
        String invalidPassword = "passwordWithOutCharacterSpecial";

        Exception myException = null;
        try{
            createdUserWithPassword(invalidPassword);
        }catch(Exception e){
            myException = e;
        }
        assertEquals(myException.getMessage(), "Specified password "+ invalidPassword +" is invalid." );

    }







}