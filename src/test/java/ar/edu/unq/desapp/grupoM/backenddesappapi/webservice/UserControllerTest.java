package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UserControllerTest {

    UserController userController = new UserController();
    UserBuilder userBuilder = new UserBuilder();
    User user = userBuilder.build();

    @BeforeEach
    void setUp(){
        userController.createUser(user);
    }

    @MockBean
    UserService userServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    // Mock Testing
    @Test
    public void mockCreateUser() throws Exception {
        User user = UserBuilder.user().build();
        when(userServiceMock.createUser(any())).thenReturn(user);

        JSONObject body = generateUserBody(user);

        MvcResult mvcResult =  mockMvc.perform(post("http://localhost:8080/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(user.name)))
                .andExpect(jsonPath("lastName", is(user.lastName)))
                .andExpect(jsonPath("email", is(user.email)))
                .andExpect(jsonPath("address", is(user.address)))
                .andExpect(jsonPath("password", is(user.password)))
                .andExpect(jsonPath("cvu", is(user.cvu)))
                .andExpect(jsonPath("wallet", is(user.wallet)))
                .andReturn();

    }
/*
    @Test
    public void mockUpdateUser() throws Exception {
        User updateUserParams = userBuilder.build();
        User user_with_wallet = UserBuilder.user().withWallet(11111111).build();
        userController.createUser(user_with_wallet);
        when(userServiceMock.createUser(any())).thenReturn(user_with_wallet);
        when(userServiceMock.updateUser(user_with_wallet.getWallet(), updateUserParams)).thenReturn(updateUserParams);

        JSONObject body = generateUserBody(updateUserParams);
        MvcResult mvcResult = mockMvc.perform(put("http://localhost:8080/api/users/11111111")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(updateUserParams.getName())))
                .andExpect(jsonPath("lastName", is(updateUserParams.getLastName())))
                .andExpect(jsonPath("email", is(updateUserParams.getEmail())))
                .andExpect(jsonPath("address", is(updateUserParams.getAddress())))
                .andExpect(jsonPath("password", is(updateUserParams.getPassword())))
                .andExpect(jsonPath("cvu", is(updateUserParams.getCvu())))
                .andExpect(jsonPath("wallet", is(updateUserParams.getWallet())))
                .andReturn();
    }

    @Test
    public void mockGetUser() throws Exception {
        userController.createUser(user);
        when(userServiceMock.getUser(user.getWallet())).thenReturn(user);

        JSONObject body = generateUserBody(user);

        MvcResult mvcResult = mockMvc.perform(put("http://localhost:8080/api/users/" + user.getWallet().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(user.getName())))
                .andExpect(jsonPath("lastName", is(user.getLastName())))
                .andExpect(jsonPath("email", is(user.getEmail())))
                .andExpect(jsonPath("address", is(user.getAddress())))
                .andExpect(jsonPath("password", is(user.getPassword())))
                .andExpect(jsonPath("cvu", is(user.getCvu())))
                .andExpect(jsonPath("wallet", is(user.getWallet())))
                .andReturn();
    }

*/
    // Unit Testing
    @Test
    public void createUser() {
        userController.createUser(user);
        User createdUser = userController.allUsers().getBody().stream().findFirst().get();
        assertEquals(createdUser.name, user.name);

    }

    @Test
    public void getUser() {
        User getUser = userController.getUser(user.wallet).getBody();
        assertEquals(getUser.name, user.name);
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
    public void updateUser() {
        // update user params
        User updateUserParams = userBuilder.build();
        userController.updateUser(user.wallet, updateUserParams);

        assertEquals(user.name, updateUserParams.name);
    }

    @Test
    public void deleteUser() {
        // at least there is one created user
        List<User> users = userController.allUsers().getBody();
        assertEquals(users.size(), 1);

        // after deletion, there are no more users
        userController.deleteUser(user.wallet);
        assertEquals(users.size(), 0);


    }

    private JSONObject generateUserBody(User user) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.name);
        jsonObject.put("lastName", user.lastName);
        jsonObject.put("email", user.email);
        jsonObject.put("address", user.address);
        jsonObject.put("password", user.password);
        jsonObject.put("cvu", user.cvu);
        jsonObject.put("wallet", user.wallet);
        return jsonObject;
    }
}