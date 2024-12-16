package com.example.EmployeeApp.Command;

import com.example.EmployeeApp.model.SignUp;
import com.example.EmployeeApp.service.SignUpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpCommand implements Command {

    private final SignUpService signUpService;

    // Constructor takes the SignUpService as a dependency
    public SignUpCommand(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        if (!password.equals(repeatPassword)) {
            // If passwords don't match, set an error message and forward to the registration page
            request.setAttribute("errorMessage", "Passwords do not match");
            try {
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during registration process");
            }
            return;
        }

        try {
            // Attempt to save the user credentials
            signUpService.saveUserCredential(new SignUp(name, password));
            response.sendRedirect("login.jsp"); // Redirect to login page if successful
        } catch (RuntimeException | SQLException | InterruptedException e) {
            // If a runtime exception occurs (e.g., duplicate username), set the error message
            request.setAttribute("errorMessage", e.getMessage());
            try {
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            } catch (Exception ex) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during registration process");
            }
        }
    }
}
