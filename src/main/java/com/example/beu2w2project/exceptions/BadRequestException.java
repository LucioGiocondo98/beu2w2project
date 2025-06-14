package com.example.beu2w2project.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Objects;
@Getter
public class BadRequestException extends RuntimeException {
    private final List<ObjectError> errorList;

    public BadRequestException(String message) {
        super(message);
        this.errorList = null;
    }

    public BadRequestException(List<ObjectError> errorList) {
        super("Errore di validazione nel payload");
        this.errorList = errorList;
    }
}
