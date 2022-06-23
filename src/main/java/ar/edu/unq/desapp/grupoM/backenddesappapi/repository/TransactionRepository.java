package ar.edu.unq.desapp.grupoM.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findTransactionsByUserOrInterestedUserAndStatusAndDateIsBetween(@NotNull User user, @NotNull User interestedUser,
                                                                                      @NotNull Transaction.Status status, @NotNull LocalDateTime date,
                                                                                      @NotNull LocalDateTime date2);
    Transaction findTransactionsByUserOrInterestedUser(@NotNull User user, @NotNull User interestedUser);

    List<Transaction> findTransactionsByStatus(@NotNull Transaction.Status status);
}
