package ar.edu.unq.desapp.grupoM.backenddesappapi.controller.exception_handle;

import ar.edu.unq.desapp.grupoM.backenddesappapi.model.exceptions.*;
import org.json.JSONObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    // Auxiliar methods
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity handleException(TransactionNotFoundException transactionException) {
        String transaction_invalid_json = new JSONObject()
                .put("message", transactionException.getMessage()).toString();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(transaction_invalid_json);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    // This is the exception raised when we try to find a crypto by its symbol/name from user CurrencyService. It should have a custom exception
    public ResponseEntity handleException(IndexOutOfBoundsException e) {
        String invalid_json = new JSONObject()
                .put("message", "Crypto not found").toString();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(invalid_json);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleException(UserNotFoundException userException) {
        String invalid_json = new JSONObject()
                .put("message", userException.getMessage()).toString();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(invalid_json);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleException(NullPointerException e) {
        String invalid_json = new JSONObject()
                .put("message", "User or Transaction invalid").toString();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(invalid_json);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity handleException(InvalidUserException userException) {
        String user_invalid_json = new JSONObject()
                .put("message", userException.getMessage()).toString();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(user_invalid_json);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity handleException(InvalidTransactionException transactionException) {
        String transaction_invalid_json = new JSONObject()
                .put("message", transactionException.getMessage()).toString();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(transaction_invalid_json);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    // This is the exception raised by binance when passing an invalid crypto name
    public ResponseEntity handleException(HttpClientErrorException e) {
        String transaction_invalid_json = new JSONObject()
                .put("message","Crypto not found").toString();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(transaction_invalid_json);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleException(EmptyResultDataAccessException e) {
        String transaction_invalid_json = new JSONObject()
                .put("message","User not found").toString();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(transaction_invalid_json);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity handleException(InvalidTokenException e) {
        String transaction_invalid_json = new JSONObject()
                .put("message","Name or Password Invalid").toString();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(transaction_invalid_json);
    }
}
