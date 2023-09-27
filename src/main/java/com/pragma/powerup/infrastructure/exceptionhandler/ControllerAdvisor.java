package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.UnderAgeException;
import com.pragma.powerup.infrastructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }
    @ExceptionHandler(UnderAgeException.class)
    public ResponseEntity<Map<String, String>> handleUnderAgeException(UnderAgeException underAgeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "debe ser mayor de edad"));
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistException(EmailAlreadyExistException emailAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, "El email ya se encuentra registrado"));
    }
    @ExceptionHandler(DocumentNumberAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleDocumentNumberAlreadyExistException(DocumentNumberAlreadyExistException documentNumberAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, "El documento ya se encuentra registrado"));
    }
    @ExceptionHandler(PhoneAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handlePhoneAlreadyExistException(PhoneAlreadyExistException phoneAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, "El numero celular ya se encuentra registrado"));
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmailNotFoundException(EmailNotFoundException emailNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, "Ingrese un email valido"));
    }

}