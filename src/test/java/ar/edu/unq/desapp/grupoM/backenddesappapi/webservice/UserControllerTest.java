package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
class UserControllerTest {

    UserController userController = new UserController();
    UserBuilder userBuilder = new UserBuilder();

    @Mock
    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    User user = userBuilder.build();

    @BeforeEach
    void setUp(){
        userController.createUser(user);
    }
/*
    @Test
    void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived(){
            // Given
        HttpURLConnection connection = (HttpURLConnection) "https://api.github.com/users/\" + user.getWallet()".openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + authEncoded);

            if (ignoreInvalidCertificate){
                connection.setHostnameVerifier(new InvalidCertificateHostVerifier());
            }

            return connection;
        }
        // When
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

            // Then
            assertThat(
                    httpResponse.getStatusLine().getStatusCode(),
                    equalTo(HttpStatus.SC_NOT_FOUND));
        }
    }

    @Test
    void createUser2() {
        ResponseEntity<User> userResponse = new ResponseEntity<>(user, HttpStatus.OK);

        Mockito
                .when(restTemplate.getForEntity("/adsads/asdasd", User.class))
                .thenReturn(userResponse);

        Assertions.assertEquals(userResponse.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(userResponse.getBody().getName(), user.getName());
    }

 */

    @Test
    public void createUser() {
        userController.createUser(user);
        User createdUser = userController.allUsers().getBody().stream().findFirst().get();
        assertEquals(createdUser.getName(), user.getName());

    }

    @Test
    public void getUser() {
        User getUser = userController.getUser(user.getWallet()).getBody();
        assertEquals(getUser.getName(), user.getName());
    }

    @Test
    public void getAllUser() {
        // at least there is one user by default because of setup
        // we add another user to check a list of them
        User another_user = userBuilder.build();
        userController.createUser(another_user);
        List<User> getAllUsers = userController.allUsers().getBody();

        assertEquals(getAllUsers.size(), 2);

    }

    @Test
    public void deleteUser() {
        // at least there is one created user
        List<User> users = userController.allUsers().getBody();
        assertEquals(users.size(), 1);

        // after deletion, there are no more users
        userController.deleteUser(user.getWallet());
        assertEquals(users.size(), 0);


    }
}