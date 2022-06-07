package ar.edu.unq.desapp.grupoM.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
