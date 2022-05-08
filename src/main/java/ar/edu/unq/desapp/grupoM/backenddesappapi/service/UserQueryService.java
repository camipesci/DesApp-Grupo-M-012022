package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryService {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> getUserById(Long accountId) {
        return  userRepository.findById(accountId);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}