package ar.edu.unq.desapp.grupoM.backenddesappapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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