package com.example.EmployeeApp.Command;

import com.example.EmployeeApp.model.Employee;
import com.example.EmployeeApp.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class SaveEmployeeCommand implements Command {

    private final EmployeeService employeeService;

    // Constructor takes EmployeeService as dependency
    public SaveEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Extract parameters from the request
        String name = request.getParameter("name");
        String role = request.getParameter("role");

        try {
            // Create an Employee object and save it
            employeeService.saveEmployee(new Employee(name, role));
            response.sendRedirect("controller?action=data");  // Redirect to employee list page after saving
        } catch (SQLException | InterruptedException e) {
            // Handle exceptions and log them if needed
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving employee");
        }
    }
}
