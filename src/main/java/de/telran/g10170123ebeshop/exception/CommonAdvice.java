package de.telran.g10170123ebeshop.exception;

import de.telran.g10170123ebeshop.exception.exceptions.CustomerIdDoesNotExistException;
import de.telran.g10170123ebeshop.exception.exceptions.EntityValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonAdvice {

    @ExceptionHandler(CustomerIdDoesNotExistException.class)
    public ResponseEntity<Response> handleException(CustomerIdDoesNotExistException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<Response> handleException(EntityValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
    }
}