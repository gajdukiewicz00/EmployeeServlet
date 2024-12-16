package com.example.EmployeeApp.Command;

import com.example.EmployeeApp.model.LogIn;
import com.example.EmployeeApp.service.LogInService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogInCommand implements Command {

    private final LogInService logInService;

    public LogInCommand(LogInService logInService) {
        this.logInService = logInService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (logInService.checkUserCredential(new LogIn(login, password))) {
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                resp.sendRedirect("welcome.jsp");
            } else {
                req.setAttribute("errorMessage", "User with " + login + " not exist or password is not correct");
                resp.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during login", e);
        }
    }
}
