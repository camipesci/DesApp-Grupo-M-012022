package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.UserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.UserNotFoundException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.mock.MockUserGenerateService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "User services")
@Tag(name = "User services", description = "Manage users")
@RestController
public class UserController {
    @Autowired
    MockUserGenerateService dummyUserGenerateService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Create a user")
    @PostMapping("/api/users")
    @ResponseBody
    public ResponseEntity<UserDTO> createUser(@RequestBody User newUser) {
        User user = userService.createUser(newUser.name, newUser.lastName, newUser.email, newUser.address,
                newUser.password);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTO.from(user));
    }

    @Operation(summary = "get all users")
    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(UserDTO.from(users));
    }

    @Operation(summary = "get a user by id")
    @GetMapping("/api/users/{user_id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Long user_id) throws UserNotFoundException{
        User user = userService.findUser(user_id);
        return ResponseEntity.ok().body(UserDTO.from(user));
    }

    @Operation(summary = "updates a user by id ")
    @PutMapping("/api/users/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        User updatedUser = userService.updateUser(id, updateUser.name, updateUser.lastName, updateUser.email, updateUser.address,
                updateUser.password, updateUser.cvu, updateUser.wallet);
        return ResponseEntity.ok().body(UserDTO.from(updatedUser));
    }

    @Operation(summary = "delete a user")
    @DeleteMapping("/api/users/{user_id}")
    public ResponseEntity deleteUser(@PathVariable Long user_id) throws EmptyResultDataAccessException {
        userService.deleteUser(user_id);
        return ResponseEntity.ok().body("User deleted");
    }

    // mock

    @GetMapping("/api/dummy_data")
    public void generateDummyUsers() {
        dummyUserGenerateService.generateUsers();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleException(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleException(EmptyResultDataAccessException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    }
