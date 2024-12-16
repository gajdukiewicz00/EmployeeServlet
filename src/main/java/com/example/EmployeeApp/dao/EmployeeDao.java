package com.example.EmployeeApp.dao;

import com.example.EmployeeApp.connection.ConnectionPoolInterface;
import com.example.EmployeeApp.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private final ConnectionPoolInterface connectionPool;

    public EmployeeDao(ConnectionPoolInterface connectionPool){
        this.connectionPool = connectionPool;
    }

    public List<Employee> getAll() throws InterruptedException, SQLException {

        List<Employee> employees = new ArrayList<>();


        Connection con = connectionPool.getConnection();
        try(
                    Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees")){
            while (rs.next()){
                employees.add(new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("role")));
            }

        }finally {
            connectionPool.releaseConnection(con);
        }
        return employees;
    }

    public void save(Employee employee) throws InterruptedException, SQLException {
        String sql = "INSERT INTO employees (name, role) VALUES (?, ?)";
        Connection con = connectionPool.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getRole());

            ps.executeUpdate();
            System.out.println("Data added to the database");

        }finally {
            connectionPool.releaseConnection(con);
        }

    }

    public void deleteById(long id) throws InterruptedException {
        String delete_sql = "DELETE FROM employees WHERE id=?";

        if (existById(id)){
            try(Connection con = connectionPool.getConnection();
                PreparedStatement ps = con.prepareStatement(delete_sql)) {
                ps.setLong(1, id);
               ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public boolean existById(long id){

        String sql = "SELECT * FROM employees WHERE id=?";

        try(Connection con = connectionPool.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

}
