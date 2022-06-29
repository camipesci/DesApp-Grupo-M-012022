package ar.edu.unq.desapp.grupoM.backenddesappapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CryptoControllerTest extends ControllerTest {

    @Test
    void getAllCryptos() {

        assertEquals(cryptoController.getAllCryptosPrice().getBody().size(), 14);
    }

    @Test
    void getCrypto() {
        String symbol ="ALICEUSDT";

        assertEquals(cryptoController.getCryptoPrice(symbol).getBody().symbol, "ALICEUSDT");
    }



}