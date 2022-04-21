package ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions;

import java.math.BigInteger;

public class InvalidCvuException extends RuntimeException {
    public InvalidCvuException(BigInteger cvu) {
        super("Specified cvu "+ cvu +" is invalid.");
    }
}