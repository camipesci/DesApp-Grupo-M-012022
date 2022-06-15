package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}