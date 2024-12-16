package com.example.EmployeeApp.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {

    /**
     * Execute the command.
     * @param req The HttpServletRequest
     * @param resp The HttpServletResponse
     * @throws Exception If any error occurs during command execution
     */

    void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
