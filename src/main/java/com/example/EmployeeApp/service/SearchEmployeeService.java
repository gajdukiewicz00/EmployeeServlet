package com.example.EmployeeApp.service;

import com.example.EmployeeApp.dao.SearchEmployeeDao;
import com.example.EmployeeApp.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class SearchEmployeeService {

    private SearchEmployeeDao searchEmployeeDao;

    public SearchEmployeeService(SearchEmployeeDao searchDao) {
        this.searchEmployeeDao = searchDao;
    }

    public List<Employee> SearchEmployeeByName(String name) throws SQLException, InterruptedException {
        return searchEmployeeDao.searchByName(name);
    }

    public List<Employee> SearchEmployeeById(long id) throws SQLException, InterruptedException {
        return searchEmployeeDao.searchById(id);
    }



}
