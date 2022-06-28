package ar.edu.unq.desapp.grupoM.backenddesappapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserTradedVolumenDTO {
    @JsonProperty
    public UserDTO user;

    @JsonProperty
    public String date;

    @JsonProperty
    public Double totalUSDVolumen;

    @JsonProperty
    public Double totalARSVolumen;

    @JsonProperty
    public List<CryptoDTO> cryptos;


    public UserTradedVolumenDTO from(UserDTO user, LocalDateTime date, List<CryptoDTO> cryptos, Double totalUSDVolumen, Double totalARSVolumen ) {
        return new UserTradedVolumenDTO(user, date, cryptos, totalUSDVolumen, totalARSVolumen);
    }


    public UserTradedVolumenDTO(UserDTO user, LocalDateTime date, List<CryptoDTO> cryptos, Double totalUSDVolumen, Double totalARSVolumen) {
        this.user = user;
        this.date = formatted_date();
        this.totalUSDVolumen = totalUSDVolumen;
        this.totalARSVolumen = totalARSVolumen;
        this.cryptos = cryptos;

    }
    public String formatted_date(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date raw_date = new Date();
        String formatted_date = formatter.format(raw_date);
        return formatted_date;
    }
}