package com.example.EmployeeApp.model;


import lombok.Data;

@Data
public class SignUp {
    private long id;
    private String username;
    private String password;

    public SignUp(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
