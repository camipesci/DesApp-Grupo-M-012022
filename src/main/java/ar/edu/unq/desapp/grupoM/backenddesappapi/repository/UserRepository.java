package ar.edu.unq.desapp.grupoM.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
