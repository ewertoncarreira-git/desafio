package br.com.concrete.desafio.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handlerRunTimeException(RuntimeException runtimeException) {
        ErrorMessage errorMessage = new ErrorMessage(runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerUserNotFundException(UserNotFoundException userNotFoundException) {
        ErrorMessage errorMessage = new ErrorMessage(userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorMessage> handlerInvalidEmailException(InvalidEmailException invalidEmailException) {
        ErrorMessage errorMessage = new ErrorMessage(invalidEmailException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<ErrorMessage> handlerInvalidNameException(InvalidNameException invalidNameException) {
        ErrorMessage errorMessage = new ErrorMessage(invalidNameException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorMessage> handlerInvalidPasswordException(InvalidPasswordException invalidPasswordException) {
        ErrorMessage errorMessage = new ErrorMessage(invalidPasswordException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
