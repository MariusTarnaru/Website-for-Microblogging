package sda.backend.server.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sda.backend.server.exception.EmailAlreadyUsedException;
import sda.backend.server.exception.UserNotFoundException;


import java.util.*;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity emailAlreadyUsedException(EmailAlreadyUsedException businessException) {
        ErrorDetails errorDetails = getErrorDetails(businessException.getMessage(), businessException.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException businessException) {
        ErrorDetails errorDetails = getErrorDetails(businessException.getMessage(), businessException.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    private ErrorDetails getErrorDetails(String message, String validationType) {
        return new ErrorDetails()
                .setMessage(message)
                .setValidationType(validationType)
                .setTimestamp(new Date());
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                          HttpHeaders headers,
                                                          HttpStatus status,
                                                          WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        List<ErrorDetails> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.add(getErrorDetails(fieldError.getDefaultMessage(), "InputValidationException"));
        }
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

}
