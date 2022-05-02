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

   /*
        Nomenclatura de API REST

        entidad
        id de entidad

        BASE_URL: localhost:8080
        API: /api
        ENTITY: users
        ID_ENTITY: user_wallet

        GET /api/users
        POST /api/users
        PUT /api/users
        DELETE /api/users
   */

    @PostMapping("/api/users")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User user = userService.createUser(newUser);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUsers();
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping("/api/users/{user_wallet}")
    public ResponseEntity<List<User>>  deleteUser(@PathVariable Integer user_wallet) {
        List<User> users = userService.deleteUser(user_wallet);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/api/users/{user_wallet}")
    public ResponseEntity<User> getUser(@PathVariable Integer user_wallet) {
        User user = userService.getUser(user_wallet);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/api/users/{user_wallet}")
    public ResponseEntity<User> updateUser(@PathVariable Integer user_wallet, @RequestBody User userUpdate) {
        User newUser = userService.updateUser(user_wallet, userUpdate);
        return ResponseEntity.ok().body(newUser);
    }
}
