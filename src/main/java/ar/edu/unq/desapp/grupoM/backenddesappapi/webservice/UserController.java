package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import java.util.*;

import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;


@RestController
@EnableAutoConfiguration
public class UserController {
   UserService userService = new UserService();

    @PostMapping("/api/createUser")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User user = userService.create_new_user(newUser);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/api/allUsers")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUsers();
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping("/api/deleteUser/{user_wallet}")
    public ResponseEntity<List<User>>  deleteUser(@PathVariable Integer user_wallet) {
        List<User> users = userService.deleteUser(user_wallet);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/api/getUser/{user_wallet}")
    public ResponseEntity<User> getUser(@PathVariable Integer user_wallet) {
        User user = userService.getUser(user_wallet);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/api/updateUser/{user_wallet}")
    public ResponseEntity<User> updateUser(@PathVariable Integer user_wallet, @RequestBody User userUpdate) {
        User newUser = userService.updateUser(user_wallet, userUpdate);
        return ResponseEntity.ok().body(newUser);
    }
}
