package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;


public class InvalidCvuException extends RuntimeException {
    public InvalidCvuException(String cvu) {
        super("Specified cvu "+ cvu +" is invalid.");
    }
}