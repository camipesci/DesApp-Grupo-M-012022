package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import java.util.*;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.InvalidEmailException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@EnableAutoConfiguration
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    List<User> users = new ArrayList<User>();


    @PostMapping("/api/createUser")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
         User user = newUser;
         users.add(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/api/allUsers")
    public ResponseEntity<List<User>> allUsers() {

        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping("/api/deleteUser/{wallet}")
    public ResponseEntity<List<User>>  deleteUser(@PathVariable Integer wallet) {

        users.removeIf(u -> u.getWallet().equals(wallet));

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/api/getUser/{wallet}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Integer wallet) {

        Optional<User> user = users.stream().
                filter(u -> u.getWallet().equals(wallet)).
                findFirst();

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/api/updateUser/{wallet}")
    public ResponseEntity<User> updateUser(@PathVariable Integer wallet, @RequestBody User userUpdate) {

        User newUser = userUpdate;
        try {
            users.add(newUser);
            //Remove all User
            users.removeIf(u -> u.getWallet().equals(wallet));
        }
        catch(InvalidEmailException e ){
            logger.info("Error delete user " + e.getMessage());
        }

        return ResponseEntity.ok().body(newUser);
    }

}
