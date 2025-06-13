package com.example.beu2w2project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorsPayload> handleBadRequest(BadRequestException ex) {
        if (ex.getErrorList() != null) {
            String message = ex.getErrorList().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            ErrorsPayloadWithList payload = new ErrorsPayloadWithList(message, LocalDateTime.now(), ex.getErrorList());
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        } else {
            ErrorsPayload payload = new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorsPayload> handleNotFound(NotFoundException ex) {
        ErrorsPayload payload = new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorsPayload> handleGenericException(Exception ex) {
        ex.printStackTrace();
        ErrorsPayload payload = new ErrorsPayload("Errore generico del server", LocalDateTime.now());
        return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public record ErrorsPayload(String message, LocalDateTime timestamp) {}
    public record ErrorsPayloadWithList(String message, LocalDateTime timestamp, java.util.List<org.springframework.validation.ObjectError> errors) {}
}