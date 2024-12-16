package com.example.EmployeeApp.controller;

import com.example.EmployeeApp.Command.*;
import com.example.EmployeeApp.connection.ConnectionPool;
import com.example.EmployeeApp.connection.ConnectionPoolInterface;
import com.example.EmployeeApp.dao.EmployeeDao;
import com.example.EmployeeApp.dao.LogInDao;
import com.example.EmployeeApp.dao.SearchEmployeeDao;
import com.example.EmployeeApp.dao.SignUpDao;
import com.example.EmployeeApp.model.Employee;
import com.example.EmployeeApp.service.EmployeeService;
import com.example.EmployeeApp.service.LogInService;
import com.example.EmployeeApp.service.SearchEmployeeService;
import com.example.EmployeeApp.service.SignUpService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/controller")
public class FrontControllerServlet extends HttpServlet {
    private EmployeeService employeeService;
    private LogInService logInService;
    private SearchEmployeeService searchEmployeeService;
    private SignUpService signUpService;
    private Command command;


    // Initialization method if needed
    @SneakyThrows
    @Override
    public void init() throws ServletException {
        ConnectionPoolInterface connectionPool = ConnectionPool.getInstance();

        // Initialization code, e.g., setting up resources or connections.
        EmployeeDao employeeDao = new EmployeeDao(connectionPool);
        this.employeeService = new EmployeeService(employeeDao);

        LogInDao logInDao = new LogInDao(connectionPool);
        this.logInService = new LogInService(logInDao);

        SearchEmployeeDao searchEmployeeDao = new SearchEmployeeDao(connectionPool);
        this.searchEmployeeService = new SearchEmployeeService(searchEmployeeDao);

        SignUpDao signUpDao = new SignUpDao(connectionPool);
        this.signUpService = new SignUpService(signUpDao);

    }

    // Handling the HTTP request
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action parameter from the request
        String action = request.getParameter("action");

        // Handle different actions based on the parameter
        if (action == null) {
            // Default action or page
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        } else {
            switch (action) {
                case "data":
                    showEmployeeList(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
//                case "search":
//                    searchEmployee(request, response);
//                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not recognized");
            }
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action parameter for POST requests
        String action = request.getParameter("action");

        // Handle different actions based on the parameter
        if (action != null) {
            switch (action) {
                case "login":
                    handleLogin(request, response);
                    break;
                case "save":
                    saveUserDetails(request, response);
                    break;
                case "data":
                    updateEmployee(request, response);
                    break;
                case "delete-employee":
                    deleteEmployee(request, response);
                    break;
                case "search":
                    searchEmployee(request, response);
                    break;
                case "signUp":
                    signUp(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not recognized");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No action specified");
        }
    }

    // Method to show the list of employees
    private void showEmployeeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        command = new EmployeeListCommand(employeeService);
        command.execute(request, response);
    }

    // Method to handle login
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        command = new LogInCommand(logInService);
        command.execute(request, response);
    }

    // Method to handle user logout
    private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        command = new LogOutCommand();
        command.execute(request, response);
    }

    // Method to save user details (like name and role)
    private void saveUserDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        command = new SaveEmployeeCommand(employeeService);
        command.execute(request, response);
    }

    private void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        command = new SearchEmployeeCommand(searchEmployeeService, employeeService);
        command.execute(request, response);

    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        command = new SignUpCommand(signUpService);
        command.execute(request, response);
    }

    // Method to update employee details
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String role = request.getParameter("role");

        // Assuming there's a method to update the employee in the database
        boolean isUpdated = updateEmployeeInDatabase(employeeId, name, role); // Assume this method is implemented

        if (isUpdated) {
            // Redirect back to employee list after update
            response.sendRedirect("controller?action=employee-list");
        } else {
            // Set error message and forward back to employee list page
            request.setAttribute("errorMessage", "Failed to update employee.");
            request.getRequestDispatcher("/employee-list").forward(request, response);
        }
    }

    // Method to delete an employee
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
       command = new DeleteEmployeeCommand(employeeService);
       command.execute(request, response);
    }

    // Dummy methods for database interaction (to be implemented)
    private List<Employee> getEmployeesFromDatabase() {
        // Return a list of employees from the database
        return new ArrayList<>();
    }



    private boolean saveUserDetailsToDatabase(String name, String role) {
        // Save user details to the database (this can be implemented with JDBC or an ORM like Hibernate)
        return true;
    }

    private boolean updateEmployeeInDatabase(int employeeId, String name, String role) {
        // Update employee details in the database
        return true;
    }

    private boolean deleteEmployeeFromDatabase(int employeeId) {
        // Delete employee from the database
        return true;
    }

    @Override
    public void destroy() {
        super.destroy();
        // Clean up resources if needed
    }
}
