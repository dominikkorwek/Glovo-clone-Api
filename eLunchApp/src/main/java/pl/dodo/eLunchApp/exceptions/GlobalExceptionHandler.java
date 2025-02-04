package pl.dodo.eLunchApp.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorWrapper> handleGeneralException(Exception e, HttpServletRequest request) {
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getResponseEntity(e.getMessage(), status, request.getRequestURI());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorWrapper> handleResponseStatusException(ResponseStatusException e, HttpServletRequest request) {
        HttpStatusCode status = e.getStatusCode();
        return getResponseEntity(e.getMessage(), status, request.getRequestURI());

    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorWrapper> handleJsonProcessingException(JsonProcessingException e, HttpServletRequest request) {
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getResponseEntity(e.getMessage(), status, request.getRequestURI());
    }

    private static ResponseEntity<ErrorWrapper> getResponseEntity(String message, HttpStatusCode status, String uri){
        ErrorWrapper errorWrapper = new ErrorWrapper(message, status, uri, status);
        return new ResponseEntity<>(errorWrapper, status);
    }
}
