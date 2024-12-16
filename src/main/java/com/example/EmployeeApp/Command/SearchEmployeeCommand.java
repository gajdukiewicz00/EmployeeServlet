package com.example.EmployeeApp.Command;

import com.example.EmployeeApp.model.Employee;
import com.example.EmployeeApp.service.EmployeeService;
import com.example.EmployeeApp.service.SearchEmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchEmployeeCommand implements Command {

    private final SearchEmployeeService searchService;
    private final EmployeeService employeeService;

    // Constructor takes both SearchEmployeeService and EmployeeService as dependencies
    public SearchEmployeeCommand(SearchEmployeeService searchService, EmployeeService employeeService) {
        this.searchService = searchService;
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, InterruptedException {
        String searchName = request.getParameter("sName");
        List<Employee> searchedEmployees;

        if (searchName == null || searchName.trim().isEmpty()) {
            // If the search input is empty, show all employees
            searchedEmployees = employeeService.getAllEmployees();
        } else {
            if (isDigit(searchName)) {
                // If the search input is numeric, search by employee ID
                searchedEmployees = searchService.SearchEmployeeById(Long.parseLong(searchName));
            } else {
                // Otherwise, search by employee name
                searchedEmployees = searchService.SearchEmployeeByName(searchName);
            }
        }

        // Set the search result as a request attribute to forward to JSP
        request.setAttribute("employeesList", searchedEmployees);
        try {
            request.getRequestDispatcher("data.jsp").forward(request, response);
            request.getRequestDispatcher("controller?action=data").forward(request, response);

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during search operation");
        }
    }

    // Helper method to check if the string is a digit
    private boolean isDigit(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
