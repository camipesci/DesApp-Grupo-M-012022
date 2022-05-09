package ar.edu.unq.desapp.grupoM.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
