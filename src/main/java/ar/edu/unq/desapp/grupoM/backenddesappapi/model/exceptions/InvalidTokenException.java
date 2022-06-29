package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String errorMessage) {
        super(errorMessage);
        this.setStackTrace(new StackTraceElement[0]);
    }

}