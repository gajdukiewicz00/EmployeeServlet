package com.example.EmployeeApp.service;

import com.example.EmployeeApp.dao.EmployeeDao;
import com.example.EmployeeApp.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void saveEmployee(Employee employee) throws SQLException, InterruptedException {
        employeeDao.save(employee);
    }

    public List<Employee> getAllEmployees() throws SQLException, InterruptedException {
        return employeeDao.getAll();
    }

    public void deleteEmployeeById(long id) throws InterruptedException {
        employeeDao.deleteById(id);
    }
}
