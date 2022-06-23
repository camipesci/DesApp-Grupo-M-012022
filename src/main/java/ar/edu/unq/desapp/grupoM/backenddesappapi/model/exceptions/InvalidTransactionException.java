package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(Long id) {
        super("Specified Transaction "+ id +" is invalid for this operation.");
    }
}