package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.MockUserGenerateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MockUserController {
    private final MockUserGenerateService dummyUserGenerateService;

    public MockUserController(MockUserGenerateService dummyUserGenerateService) {
        this.dummyUserGenerateService = dummyUserGenerateService;
    }

    @GetMapping("/api/usersmock")
    public void generateDummyUsers() {
        dummyUserGenerateService.generateUsers();
    }

    /*@GetMapping("/api/usersmock")
    public ResponseEntity<List<User>> generateDummyUsers() {
        dummyUserGenerateService.generateUsers();
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }*/

}
