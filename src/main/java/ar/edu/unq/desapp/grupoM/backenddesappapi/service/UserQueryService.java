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

    public User createUser(String name, String lastName, String email, String address, String password, String cvu, Integer wallet) {
        User newUser = new User(name, lastName, email, address, password, cvu, wallet);

        return userRepository.save(newUser);
    }

    public User updateUser(Long id, String name, String lastName, String email, String address, String password, String cvu, Integer wallet) {
        User userToModify = userRepository.findById(id).get();
        userToModify.name = name;
        userToModify.lastName = lastName;
        userToModify.email = email;
        userToModify.address = address;
        userToModify.cvu = cvu;
        userToModify.wallet = wallet;


        return userRepository.save(userToModify);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User findUser(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}

