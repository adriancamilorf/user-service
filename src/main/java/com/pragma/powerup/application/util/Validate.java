package com.pragma.powerup.application.util;

import lombok.extern.java.Log;

import java.util.regex.Pattern;

public class Validate {

    private Validate() {

    }

    public static boolean notIsString(String data) {
        return data == null || data.trim().isEmpty();
    }

    public static boolean isEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public static boolean isPhoneValid(String phone){
        String regex = "^(\\+\\d{10,12}|\\d{10,12})$";
        return phone.matches(regex);
    }

    public static boolean containsOnlyDigits(String data) {
        String regex = "^\\d+$";
        return data.matches(regex);
    }

}
