package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CryptoDTO {
    @JsonProperty
    public String symbol;

    @JsonProperty
    public Double price;

    @JsonProperty
    public String price_date;

    public static CryptoDTO from(Crypto crypto) {
        return new CryptoDTO(crypto.getSymbol(), crypto.getPrice(), crypto.getPrice_date());
    }

    public static List<CryptoDTO> from(List<Crypto> cryptos) {
        List<CryptoDTO> cryptosDTOList = new ArrayList<CryptoDTO>();
        for (Crypto crypto : cryptos)
        {
            CryptoDTO newUserDto = new CryptoDTO(crypto.getSymbol(), crypto.getPrice(), crypto.getPrice_date());
            cryptosDTOList.add(newUserDto);
        }
        return cryptosDTOList;
    }

    public CryptoDTO(String symbol, Double price, Date price_date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date raw_date = new Date();
        String formatted_date = formatter.format(raw_date);
        this.symbol = symbol;
        this.price = price;
        this.price_date = formatted_date;


    }
}