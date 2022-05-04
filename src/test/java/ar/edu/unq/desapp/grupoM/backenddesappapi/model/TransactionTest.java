package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.TransactionBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransactionTest {

    @Test
    public void createTransactionTest() {

        User user = UserBuilder.user().build();
        Transaction transaction1 = TransactionBuilder.transaction().withUser(user).build();

        assertEquals(transaction1.getUser().getName(), user.getName() );
        assertEquals(transaction1.getCryptoAmount(), 0.01 );
        assertEquals(transaction1.getCryptoPrice(), 120.10 );
        assertEquals(transaction1.getCryptoArsPrice(), 200.20 );
        assertEquals(transaction1.getCryptoCurrency(), "BNBUSDT" );

    }

}