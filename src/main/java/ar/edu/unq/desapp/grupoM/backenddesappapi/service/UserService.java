package ar.edu.unq.desapp.grupoM.backenddesappapi.service;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.webservice.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService {
    List<User> users = new ArrayList<User>();
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public  List<User> allUsers() {
        return this.users;
    }

    public User create_new_user(User newUser) {
        User user = newUser;
        users.add(user);
        return user;
    }

    public List<User> deleteUser(@PathVariable Integer user_wallet) {
        allUsers().removeIf(u -> u.getWallet().equals(user_wallet));
         return allUsers();
    }


    public User getUser(@PathVariable Integer wallet) {
        User user = allUsers().stream().filter(u -> u.getWallet().equals(wallet)).findFirst().get();
        return user;
    }

    public User updateUser(Integer user_wallet, User userUpdate) {

        User newUser = userUpdate;
        try {
            allUsers().add(newUser);
            //Remove all User
            allUsers().removeIf(u -> u.getWallet().equals(user_wallet));
        }
        catch(InvalidEmailException e ){
            logger.info("Error delete user " + e.getMessage());
        }

        return newUser;
    }
}