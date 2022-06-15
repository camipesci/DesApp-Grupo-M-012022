package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserTransactionDTO {
    @JsonProperty
    public String user;

    @JsonProperty
    public Integer user_operations;

    @JsonProperty
    public String user_reputation;

    public static UserTransactionDTO from(UserDTO user) {
        return new UserTransactionDTO(user);
    }

    public UserTransactionDTO(UserDTO user) {
        this.user = user.getCompleteName();
        this.user_operations = user.getOperations();
        this.user_reputation = user.getUserReputation();
    }
}