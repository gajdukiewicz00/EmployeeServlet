package com.example.EmployeeApp.dao;

import com.example.EmployeeApp.connection.ConnectionPoolInterface;
import com.example.EmployeeApp.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeDao {
    private final ConnectionPoolInterface connectionPool;


    public SearchEmployeeDao(ConnectionPoolInterface connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Employee> searchByName(String searchName) throws InterruptedException, SQLException {
        String sql = "SELECT * FROM employees WHERE name LIKE ?";
        List<Employee> employees = new ArrayList<>();

        Connection con = connectionPool.getConnection();

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, "%" + searchName + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                employees.add(new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("role")));
            }

        }finally {
            connectionPool.releaseConnection(con);
        }

        return employees;
    }

    public List<Employee> searchById(long id) throws SQLException, InterruptedException {
        String sql = "SELECT * FROM employees WHERE id=?";
        List<Employee> employees = new ArrayList<>();

        Connection con = connectionPool.getConnection();

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setLong(1, id );
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                employees.add(new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("role")));
            }

        }finally {
            connectionPool.releaseConnection(con);
        }

        return employees;
    }




}
