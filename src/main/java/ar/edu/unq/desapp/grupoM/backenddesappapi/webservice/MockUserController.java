package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoM.backenddesappapi.service.MockUserGenerateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mockaccount")
public class MockUserController {
    private final MockUserGenerateService dummyUserGenerateService;

    public MockUserController(MockUserGenerateService dummyUserGenerateService) {
        this.dummyUserGenerateService = dummyUserGenerateService;
    }

    @GetMapping("/generatedummyaccounts")
    public void generateDummyUsers() {
        dummyUserGenerateService.generateUsers();
    }

}
