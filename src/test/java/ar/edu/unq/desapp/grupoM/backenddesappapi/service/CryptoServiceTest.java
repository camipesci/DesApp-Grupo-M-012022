package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.Crypto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CryptoServiceTest extends ServiceTest {

    @Test
    void findCrypto() {
        String symbol ="ALICEUSDT";

        Crypto crypto = cryptoService.findCrypto(symbol);

        assertEquals(crypto.getSymbol(),"ALICEUSDT" );
    }

    @Test
    void getAllCryptos() {

        assertEquals(cryptoService.getCryptos().size(), 14);
    }

    @Test
    void updateCrypto() {

       Double priceOld = crypto.price;

       Double newPrice = 100.1;

       cryptoService.updateCrypto(crypto.symbol,newPrice);

       assertNotEquals(priceOld,newPrice);
    }

}