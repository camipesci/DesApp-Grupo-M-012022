package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(Long id) {
        super("Specified User "+ id +" is invalid for this operation.");
    }
}