package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.MockUserGenerateService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserQueryService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserQueryController {
    @Autowired
    MockUserGenerateService dummyUserGenerateService;

    @Autowired
    private UserQueryService userService;

    @PostMapping("/api/v2/users")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User user = userService.createUser(newUser.name, newUser.lastName, newUser.email, newUser.address,
                newUser.password, newUser.cvu, newUser.wallet);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/api/v2/users/{id}")
    @ResponseBody
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        User updatedUser = userService.updateUser(id, updateUser.name, updateUser.lastName, updateUser.email, updateUser.address,
                updateUser.password, updateUser.cvu, updateUser.wallet);
        return ResponseEntity.ok().body(updatedUser);
    }


    @GetMapping("/api/v2/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/api/v2/users/{user_id}")
    public ResponseEntity<User> findUser(@PathVariable Long user_id) throws Exception {
        Long id = new Long(user_id);
        User user = userService.findUser(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/api/v2/users/{user_id}")
    public ResponseEntity deleteUser(@PathVariable Long user_id) throws Exception {
        Long id = new Long(user_id);
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User deleted");
    }

    @GetMapping("/api/v2/dummy_data")
    public void generateDummyUsers() {
        dummyUserGenerateService.generateUsers();
    }
    }
