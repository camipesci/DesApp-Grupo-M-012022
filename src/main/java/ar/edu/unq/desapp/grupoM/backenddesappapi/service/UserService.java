package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.UserNotFoundException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

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
        userToModify.password = password;


        return userRepository.save(userToModify);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User findUser(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void deleteUser(Long id) throws UserNotFoundException{
        userRepository.deleteById(id);
    }
}

