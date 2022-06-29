package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.AuthUserDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.InvalidTokenException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.security.JWTUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;


    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody AuthUserDTO body) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getName(), body.getPassword());

        authManager.authenticate(authInputToken);

        String token = jwtUtil.generateToken(body.getName());

        return Collections.singletonMap("jwt-token", token);
    }
}