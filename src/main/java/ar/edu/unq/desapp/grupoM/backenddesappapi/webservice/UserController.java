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

    /*@Autowired
    private UserService userService;*/

    /*@GetMapping("/api/cars")
    public ResponseEntity<?> allUsers() {
        //List<User> list = fetchUsersFromService();
        return ResponseEntity.ok().body(list);
    }*/

    @PostMapping("/api/user")
    public ResponseEntity<Map<String, String>> testUser(@RequestBody User newUser) {
         User user = newUser;
         last_user = user;
        Map<String, String> resultado = new HashMap<String, String>();
        resultado.put("user_name", user.getName());
        resultado.put("cvu", user.getCvu().toString());
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping("/api/get_user")
    public ResponseEntity<Map<String, String>> getUser() {
        User user = last_user;
        Map<String, String> resultado = new HashMap<String, String>();
        resultado.put("user_name", user.getName());
        resultado.put("cvu", user.getCvu().toString());
        return ResponseEntity.ok().body(resultado);
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
