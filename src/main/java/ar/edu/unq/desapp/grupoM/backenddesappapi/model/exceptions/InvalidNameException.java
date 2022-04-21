package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String name) {
        super("Specified name "+ name +" is invalid.");
    }
}