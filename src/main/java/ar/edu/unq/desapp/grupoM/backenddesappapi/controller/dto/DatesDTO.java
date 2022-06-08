package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.CryptoCurrency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class DatesDTO {
    @JsonProperty
    public String from;

    @JsonProperty
    public String to;


    public static DatesDTO from(String from, String to) {
        return new DatesDTO(from, to);
    }

    public DatesDTO(String from, String to) {
        this.from = from;
        this.to = to;
    }
}