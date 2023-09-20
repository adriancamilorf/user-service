package com.pragma.powerup.application.validation;

import com.pragma.powerup.application.dto.request.NewUserRequestDto;
import com.pragma.powerup.application.exception.InvalidRequestException;
import lombok.extern.java.Log;

import static com.pragma.powerup.application.util.Validate.*;

@Log
public class UserValidate {

    private UserValidate(){
    }

    public static NewUserRequestDto userValidate(NewUserRequestDto newUserRequestDto){
        if(newUserRequestDto==null){
            throw new InvalidRequestException("Datos no validados");
        }
        if(notIsString(newUserRequestDto.getName())){
            throw new InvalidRequestException("EL nombre es obligatorio");
        }
        if(notIsString(newUserRequestDto.getLastName())){
            throw new InvalidRequestException("EL apellido es obligatorio");
        }
        if(notIsString(newUserRequestDto.getDocumentNumber()) || !containsOnlyDigits(newUserRequestDto.getDocumentNumber())){
            throw new InvalidRequestException("EL numero de documento es obligatorio");
        }
        if(notIsString(newUserRequestDto.getPhone()) || !isPhoneValid(newUserRequestDto.getPhone())){
            throw new InvalidRequestException("EL telefono es obligatorio");
        }
        if(notIsString(newUserRequestDto.getEmail()) || !isEmail(newUserRequestDto.getEmail()) ){
            throw new InvalidRequestException("Email no valido");
        }
        if(newUserRequestDto.getBirthdate()==null){
            throw new InvalidRequestException("La fecha de nacimiento es obligatoria");
        }
        if(notIsString(newUserRequestDto.getPassword())){
            throw new InvalidRequestException("La contraseña es obligatoria");
        }

        return newUserRequestDto;
    }

}
