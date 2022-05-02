package ar.edu.unq.desapp.grupoM.backenddesappapi.service;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.webservice.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;



@Service
public class UserService {
    List<User> users = new ArrayList<User>();
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public  List<User> allUsers() {
        return this.users;
    }

    public User createUser(User newUser) {
        User user = newUser;
        users.add(user);
        return user;
    }

    public List<User> deleteUser(@PathVariable Integer user_wallet) {
        allUsers().removeIf(u -> u.getWallet().equals(user_wallet));
         return allUsers();
    }


    public User getUser(@PathVariable Integer user_wallet) {
        User user = allUsers().stream().filter(u -> u.getWallet().equals(user_wallet)).findFirst().get();
        return user;
    }

    public User updateUser(Integer user_wallet, User userUpdate) {
        User newUser = userUpdate;
        User userToModify = null;
        try {
            userToModify = allUsers().stream().filter(u -> u.getWallet().equals(user_wallet)).findFirst().get();
            userToModify.setAddress(userUpdate.getAddress());
            userToModify.setCvu(userUpdate.getCvu());
            userToModify.setEmail(userUpdate.getEmail());
            userToModify.setLastName(userUpdate.getLastName());
            userToModify.setName(userUpdate.getName());
            userToModify.setPassword(userUpdate.getPassword());
            userToModify.setWallet(userUpdate.getWallet());
        }
        catch(InvalidEmailException e ){
            logger.info("Error while updating user " + e.getMessage());
        }

        return userToModify;
    }
}