package com.example.EmployeeApp.service;

import com.example.EmployeeApp.dao.SignUpDao;
import com.example.EmployeeApp.model.SignUp;

import java.sql.SQLException;

public class SignUpService {
    private final SignUpDao signUpDao;


    public SignUpService(SignUpDao signUpDao) {
        this.signUpDao = signUpDao;
    }

    public void saveUserCredential(SignUp signUp) throws SQLException, InterruptedException {
        signUpDao.save(signUp);
    }
}
