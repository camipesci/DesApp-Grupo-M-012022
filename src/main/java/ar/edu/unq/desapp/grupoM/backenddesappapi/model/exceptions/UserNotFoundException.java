package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}