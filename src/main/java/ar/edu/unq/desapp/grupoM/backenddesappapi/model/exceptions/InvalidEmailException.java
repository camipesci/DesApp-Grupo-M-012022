package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("Specified email "+ email +" format is invalid.");
    }
}