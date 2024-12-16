package com.example.EmployeeApp.Command;

import com.example.EmployeeApp.model.Employee;
import com.example.EmployeeApp.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class EmployeeListCommand implements Command {

    private final EmployeeService employeeService;

    public EmployeeListCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Employee> employeesList = employeeService.getAllEmployees();
            System.out.println(employeesList);
            req.setAttribute("employeesList", employeesList);
            req.getRequestDispatcher("data.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving employee data", e);
        }
    }
}
