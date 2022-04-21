package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String address) {
        super("Specified address "+ address +" is invalid.");
    }
}