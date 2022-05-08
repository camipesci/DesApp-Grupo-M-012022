package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.MockUserGenerateService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserQueryService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MockUserController {
    private final MockUserGenerateService dummyUserGenerateService;
    private UserQueryService userService = new UserQueryService();

    public MockUserController(MockUserGenerateService dummyUserGenerateService) {
        this.dummyUserGenerateService = dummyUserGenerateService;
    }

    @GetMapping("/api/usersmock")
    public void generateDummyUsers() {
        dummyUserGenerateService.generateUsers();
    }

    @GetMapping("/api/getUsers")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/api/findUserById")
    public ResponseEntity<User> findUserById() throws Exception {
        Long id = Long.valueOf(1);
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }


}
