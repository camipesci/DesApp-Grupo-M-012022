package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidWalletException extends RuntimeException {
    public InvalidWalletException(Integer wallet) {
        super("Specified wallet "+ wallet +" is invalid.");
    }
}