package ar.edu.unq.desapp.grupoM.backenddesappapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthUserDTO {

    private String name;
    private String password;

}