package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;


import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.controller.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BinanceControllerTest extends TestControllerHelper {

    @Test
    void getCryptoPrice() {
        CryptoDTO cryptoCurrency = binanceController.getCryptoPrice(crypto.symbol).getBody();

        assertEquals(cryptoCurrency.symbol, crypto.symbol);

    }
}