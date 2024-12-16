package com.example.EmployeeApp.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptHashing{

    public String hashPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public  boolean verifyPassword(String inputPassword, String storedHash){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(inputPassword, storedHash);
    }

}
