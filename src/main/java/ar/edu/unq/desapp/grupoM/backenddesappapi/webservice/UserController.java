package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
//import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;

@RestController
@EnableAutoConfiguration
public class UserController {
    User last_user = null;

    @PostMapping("/api/createUser")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
         User user = newUser;
         last_user = user;
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/api/allUsers")
    public ResponseEntity<User> allUsers() {

        return ResponseEntity.ok().body(last_user);
    }


    @RequestMapping(value = "/api/version", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getVersion() {
        String version = "0.2.2";
        Map<String, String> resultado = new HashMap<String, String>();
        resultado.put("version", version);
        return ResponseEntity.ok().body(resultado);
    }

}
