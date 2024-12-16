package com.example.EmployeeApp.dao;

import com.example.EmployeeApp.connection.ConnectionPoolInterface;
import com.example.EmployeeApp.exception.UserAlreadyExistsException;
import com.example.EmployeeApp.model.SignUp;
import com.example.EmployeeApp.security.BCryptHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDao {
    private final ConnectionPoolInterface connectionPool;
    private BCryptHashing bCryptHashing;


    public SignUpDao(ConnectionPoolInterface connectionPool) {
        this.connectionPool = connectionPool;
    }


    public void save(SignUp signUp) throws InterruptedException, SQLException {
        String sql = "INSERT INTO login (username, password) VALUES(?,?)";
        Connection con = connectionPool.getConnection();
        bCryptHashing = new BCryptHashing();
        String hashedPass = bCryptHashing.hashPassword(signUp.getPassword());

        if (!existByUsername(signUp.getUsername())){

            try(PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, signUp.getUsername());
                ps.setString(2, hashedPass);
                ps.executeUpdate();
            }finally {
                connectionPool.releaseConnection(con);
            }
        }else{
            System.out.println("User with " + signUp.getUsername() + " already exist");
            throw new UserAlreadyExistsException("User with " + signUp.getUsername() + " already exist");
        }

    }

    public boolean existByUsername(String username){
        String sql = "SELECT * FROM login WHERE username=?";
        System.out.println("Calling existByUsername");
        try(Connection con = connectionPool.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
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
