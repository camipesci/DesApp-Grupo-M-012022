package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;
import lombok.Builder;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Crypto.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "crypto_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Crypto crypto;
    @NotNull
    public Double cryptoAmount;
    @NotNull
    public Double cryptoPrice;
    @NotNull
    public Double cryptoArsPrice;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "interested_user_id", referencedColumnName = "user_id")
    private User interestedUser;
    @NotNull
    public Transaction.Type type;
    @NotNull
    public Transaction.Status status;
    @NotNull
    public LocalDateTime date;

    public User getUser() {
        return user;
    }

    public User getUserId() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum Type {
        PURCHASE,
        SALE
    }

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELED
    }

    public Transaction(@NotNull Crypto crypto, @NotNull Double cryptoAmount, @NotNull Double cryptoPrice
            , @NotNull Double cryptoArsPrice, @NotNull User user, @NotNull Transaction.Type type) {
        this.crypto = crypto;
        this.cryptoAmount = cryptoAmount;
        this.cryptoPrice = cryptoPrice;
        this.cryptoArsPrice = cryptoArsPrice;
        this.user = user;
        this.type = type;
        this.date = LocalDateTime.now();
        this.status = Status.PENDING;
    }
}
