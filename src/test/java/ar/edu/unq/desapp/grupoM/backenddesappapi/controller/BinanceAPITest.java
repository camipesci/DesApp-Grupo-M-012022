package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;


import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.external_api.BinanceAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BinanceAPITest extends TestControllerHelper {
    @Autowired
    BinanceAPI binanceAPI;

    @Test
    void getCryptoPrice() {
        CryptoDTO cryptoCurrency = CryptoDTO.from(binanceAPI.call(crypto.symbol));

        assertEquals(cryptoCurrency.symbol, crypto.symbol);

    }
}