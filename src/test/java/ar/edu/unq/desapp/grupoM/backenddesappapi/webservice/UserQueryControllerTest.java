package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.UserBuilder;
import ar.edu.unq.desapp.grupoM.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.MockUserGenerateService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserQueryService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserQueryControllerTest {

/*
    UserQueryController userQueryController = new UserQueryController() ;
    UserBuilder userBuilder = new UserBuilder();
    User user = userBuilder.build();

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

    @Test
   public void createUser() {
        userQueryController.createUser(user);
        User createdUser = userQueryController.getUsers().getBody().stream().findFirst().get();
        assertEquals(createdUser.name, user.name);
    }

    */

}