package ar.edu.unq.desapp.grupoM.backenddesappapi.external_api;


import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.CryptoBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.dto.CryptoDTO;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BinanceAPITest {
    BinanceAPI binanceAPI = new BinanceAPI();
    CryptoBuilder cryptoBuilder = new CryptoBuilder();
    Crypto crypto = cryptoBuilder.build();

    @Test
    void getCryptoPrice() {
        Crypto crypto_from_binance = binanceAPI.call(crypto.symbol);

        assertEquals(crypto_from_binance.getSymbol(), crypto.symbol);
        assertNotEquals(crypto_from_binance.getPrice(), crypto.getPrice());

    }
}