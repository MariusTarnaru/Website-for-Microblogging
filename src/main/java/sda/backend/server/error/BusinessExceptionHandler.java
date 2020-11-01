package sda.backend.server.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sda.backend.server.exception.EmailAlreadyUsedException;
import sda.backend.server.exception.UserNotFoundException;

import java.util.Date;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity emailAlreadyUsedException(EmailAlreadyUsedException businessException) {
        ErrorDetails errorDetails = getErrorDetails(businessException.getMessage(), businessException.getClass().getSimpleName(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException businessException) {
        ErrorDetails errorDetails = getErrorDetails(businessException.getMessage(), businessException.getClass().getSimpleName(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    private ErrorDetails getErrorDetails(String message, String validationType, HttpStatus status) {
        return new ErrorDetails()
                .setMessage(message)
                .setDetails(status.toString())
                .setValidationType(validationType)
                .setTimestamp(new Date());
    }

}
