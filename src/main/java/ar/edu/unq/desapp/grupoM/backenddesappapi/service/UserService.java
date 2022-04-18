/*package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User save(User model) {
        return this.repository.save(model);
    }

    public User findByID(Integer id) {
        return this.repository.findById(id).get();
    }

    @Transactional
    public List<User> findAll() {
        return this.repository.findAll();
    }
}
*/