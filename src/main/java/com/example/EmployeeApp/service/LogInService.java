package com.example.EmployeeApp.service;

import com.example.EmployeeApp.dao.LogInDao;
import com.example.EmployeeApp.model.LogIn;

import java.sql.SQLException;

public class LogInService {

    private final LogInDao logInDao;

    public LogInService(LogInDao logInDao) {
        this.logInDao = logInDao;
    }

    public boolean checkUserCredential(LogIn logIn) throws SQLException, ClassNotFoundException, InterruptedException {
        return logInDao.checkCredential(logIn);
    }

}
