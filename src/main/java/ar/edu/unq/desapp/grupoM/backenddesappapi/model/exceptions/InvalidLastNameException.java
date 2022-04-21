package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidLastNameException extends RuntimeException {
    public InvalidLastNameException(String last_name) {
        super("Specified last name "+ last_name +" is invalid.");
    }
}