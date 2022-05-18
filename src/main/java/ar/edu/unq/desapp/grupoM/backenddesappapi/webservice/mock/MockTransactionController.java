package ar.edu.unq.desapp.grupoM.backenddesappapi.webservice.mock;

import ar.edu.unq.desapp.grupoM.backenddesappapi.service.mock.MockTransactionGenerateService;
import ar.edu.unq.desapp.grupoM.backenddesappapi.service.TransactionQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MockTransactionController {

    private final MockTransactionGenerateService dummyTransactionGenerateService;
    @Autowired
    private TransactionQueryService transactionQueryService;

    public MockTransactionController(MockTransactionGenerateService dummyTransactionGenerateService) {
        this.dummyTransactionGenerateService = dummyTransactionGenerateService;
    }

    @GetMapping("/api/dummy_transactions")
    public void generateDummyTransactions() {
        dummyTransactionGenerateService.generateTransaction();
    }
}
