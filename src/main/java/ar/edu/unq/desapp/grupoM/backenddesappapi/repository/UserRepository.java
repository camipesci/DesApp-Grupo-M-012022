package ar.edu.unq.desapp.grupoM.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
