package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    @JsonProperty
    public Long id;

    @JsonProperty
    public String name;

    @JsonProperty
    public String lastName;

    @JsonProperty
    public String email;

    @JsonProperty
    public String address;

    @JsonProperty
    public String cvu;

    @JsonProperty
    public Integer wallet;

    @JsonProperty
    public Integer score;

    @JsonProperty
    public Integer operations;

    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getCvu(), user.getWallet(),
                            user.getScore(), user.getOperations());
    }

    public static List<UserDTO> from(List<User> users) {
        List<UserDTO> usersDTOList = new ArrayList<UserDTO>();
        for (User user : users)
        {
            UserDTO newUserDto = new UserDTO(user.getId(), user.name, user.lastName, user.email, user.address, user.cvu, user.wallet, user.score, user.operations);
            usersDTOList.add(newUserDto);
        }
        return usersDTOList;
    }

    public UserDTO(Long id, String name, String lastName, String email, String address, String cvu, Integer wallet,
                   Integer score, Integer operations) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.cvu = cvu;
        this.wallet = wallet;
        this.score = score;
        this.operations = operations;
    }
}