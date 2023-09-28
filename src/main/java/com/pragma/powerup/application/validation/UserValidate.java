package com.pragma.powerup.application.validation;

import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.application.exception.InvalidRequestException;
import lombok.extern.java.Log;

import java.time.LocalDate;

import static com.pragma.powerup.application.util.Validate.*;

@Log
public class UserValidate {

    private UserValidate(){
    }

    public static void validateNotNull(Object user) {
        if (user == null) {
            throw new InvalidRequestException("Datos no validados");
        }
    }

    public static void validateName(String name) {
        if (notIsString(name)) {
            throw new InvalidRequestException("El nombre es obligatorio");
        }
    }

    public static void validateLastName(String lastName) {
        if (notIsString(lastName)) {
            throw new InvalidRequestException("El apellido es obligatorio");
        }
    }

    public static void validateDocumentNumber(String documentNumber) {
        if (notIsString(documentNumber) || !containsOnlyDigits(documentNumber)) {
            throw new InvalidRequestException("El número de documento es obligatorio");
        }
    }

    public static void validatePhone(String phone) {
        if (notIsString(phone) || !isPhoneValid(phone)) {
            throw new InvalidRequestException("El teléfono es obligatorio");
        }
    }

    public static void validateEmail(String email) {
        if (notIsString(email) || !isEmail(email)) {
            throw new InvalidRequestException("Email no válido");
        }
    }

    public static void validatePassword(String password) {
        if (notIsString(password)) {
            throw new InvalidRequestException("La contraseña es obligatoria");
        }
    }


    public static void birthdateValidate(LocalDate birthdate){
        if(birthdate==null){
            throw new InvalidRequestException("La fecha de nacimiento es obligatoria");
        }
    }

}
