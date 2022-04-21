package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String password) {
        super("Specified password "+ password +" is invalid.");
    }
}