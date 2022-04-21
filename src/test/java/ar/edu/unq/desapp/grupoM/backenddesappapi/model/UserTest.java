package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import static org.junit.jupiter.api.Assertions.*;
/*
package ar.edu.unq.desapp.grupoa.backenddesappapi.model;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.PlatformBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.ReportBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.ReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.TitleBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.Report;
 */
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest
public class UserTest {

    @Test
    public void userCreation() {
        BigInteger cvu = new BigInteger("1234567890987654321234");
        String name = "Juan";
        User user = new User(name,"Perez","juanperez123@gmail.com","quilmes oeste","Password123@",cvu,12345678);
        assertThat(user.getName()).isEqualTo(name);

    }








}
