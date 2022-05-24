package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import static org.junit.jupiter.api.Assertions.*;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigInteger;

@SpringBootTest
public class UserTest {

    //Valid User Creation
    @Test
    public void UserWithName() {
        User ricardo = UserBuilder.user().withName("Ricardo").build();
        assertEquals(ricardo.getName(), "Ricardo");
    }

    @Test
    public void UserWithLastname() {
        User ricardo = UserBuilder.user().withLastname("Fort").build();
        assertEquals(ricardo.getLastName(), "Fort");
    }

    @Test
    public void UserWithAddress() {
        User ricardo = UserBuilder.user().withAddress("Av. Mitre 1030").build();
        assertEquals(ricardo.getAddress(), "Av. Mitre 1030");
    }

    @Test
    public void UserWithEmail() {
        User ricardo = UserBuilder.user().withEmail("ricardoFort@gmail.com").build();
        assertEquals(ricardo.getEmail(), "ricardoFort@gmail.com");
    }


    @Test
    public void UserWithPassword() {
        User ricardo = UserBuilder.user().withPassword("Ricardo123@").build();
        assertEquals(ricardo.getPassword(), "Ricardo123@");
    }

    // InValid User Creation

    @Test
    public void UserCannotHaveAnEmptyName(){
        assertThrows(InvalidNameException.class , ()->  UserBuilder.user().withName("").build() );
    }

    @Test
    public void UserCannotHaveAnEmptyLastname(){
        assertThrows(InvalidLastNameException.class , ()->  UserBuilder.user().withLastname("").build() );
    }

    @Test
    public void UserCannotHaveAnEmptyEmail(){
        assertThrows(InvalidEmailException.class , ()->  UserBuilder.user().withEmail("").build() );
    }

    @Test
    public void UserCannotHaveAnEmptyAddress(){
        assertThrows(InvalidAddressException.class , ()->  UserBuilder.user().withAddress("").build() );
    }

    @Test
    public void UserCannotHaveAnEmptyPassword(){
        assertThrows(InvalidPasswordException.class , ()->  UserBuilder.user().withPassword("").build() );
    }

    @Test
    public void UserWithAShortName(){
        assertThrows(InvalidNameException.class , ()->  UserBuilder.user().withName("a").build() );
    }

    @Test
    public void UserWithAShortLastname(){
        assertThrows(InvalidLastNameException.class , ()->  UserBuilder.user().withLastname("b").build() );
    }

    @Test
    public void UserWithInvalidEmail(){
        assertThrows(InvalidEmailException.class , ()->  UserBuilder.user().withEmail("a").build() );
    }

    @Test
    public void UserWithInvalidAddress(){
        assertThrows(InvalidAddressException.class , ()->  UserBuilder.user().withAddress("a").build() );
    }

    @Test
    public void UserWithInvalidPassword(){
        assertThrows(InvalidPasswordException.class , ()->  UserBuilder.user().withPassword("123").build() );
    }
















}
