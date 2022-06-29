package ar.edu.unq.desapp.grupoM.backenddesappapi.service;


import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.UserNotFoundException;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(String name, String lastName, String email, String address, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(name, lastName, email, address, encodedPassword);

        return userRepository.save(newUser);
    }

    public User updateUser(Long id, String name, String lastName, String email, String address, String password) {
        User userToModify = this.findUser(id);
        userToModify.name = name;
        userToModify.lastName = lastName;
        userToModify.email = email;
        userToModify.address = address;
        userToModify.password = password;


        return userRepository.save(userToModify);
    }

    public void updateUserScore(User user, Integer score){
        User userToModify = userRepository.findById(user.getUser_id()).get();
        userToModify.setScore(userToModify.getScore() + score);
        userRepository.save(userToModify);
    }

    public void updateUserOperations(User user){
        User userToModify = userRepository.findById(user.getUser_id()).get();
        userToModify.setOperations(userToModify.getOperations() + 1);
        userRepository.save(userToModify);
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

