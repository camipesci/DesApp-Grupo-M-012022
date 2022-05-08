package ar.edu.unq.desapp.grupoM.backenddesappapi.service;

import org.springframework.stereotype.Service;

@Service
public class UserIdGenerationService {
    public Long newUserId() {
        return System.nanoTime();
    }
}
