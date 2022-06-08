package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserTradedVolumenDTO {
    @JsonProperty
    public LocalDateTime date;

    @JsonProperty
    public UserDTO user;

    @JsonProperty
    public List<CryptoDTO> cryptos;

    @JsonProperty
    public Double totalUSDVolumen;

    @JsonProperty
    public Double totalARSVolumen;




    public UserTradedVolumenDTO from(UserDTO user, LocalDateTime date, List<CryptoDTO> cryptos, Double totalUSDVolumen, Double totalARSVolumen ) {
        return new UserTradedVolumenDTO(user, date, cryptos, totalUSDVolumen, totalARSVolumen);
    }


    public UserTradedVolumenDTO(UserDTO user, LocalDateTime date, List<CryptoDTO> cryptos, Double totalUSDVolumen, Double totalARSVolumen) {
        this.user = user;
        this.date = date;
        this.cryptos = cryptos;
        this.totalUSDVolumen = totalUSDVolumen;
        this.totalARSVolumen = totalARSVolumen;
    }
}