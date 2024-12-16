package com.example.EmployeeApp.Command;

import com.example.EmployeeApp.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteEmployeeCommand implements Command {

    private final EmployeeService employeeService;

    // Constructor takes the EmployeeService as a dependency
    public DeleteEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long employeeId = Long.parseLong(request.getParameter("id"));

        try {
            // Perform the deletion
            employeeService.deleteEmployeeById(employeeId);
            // Redirect to employee list after successful deletion
//            response.sendRedirect("data.jsp");
            response.sendRedirect("controller?action=data");

        } catch (Exception e) {
            // Handle any exceptions that occur during the deletion
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting employee");
        }
    }
}
