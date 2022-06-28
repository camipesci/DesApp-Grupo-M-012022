package ar.edu.unq.desapp.grupoM.backenddesappapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDTO {

    public String name;
    public String lastName;
    public String email;
    public String address;
    public String password;

    public UserCreateDTO(String name, String lastName, String email, String address, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
    }


}