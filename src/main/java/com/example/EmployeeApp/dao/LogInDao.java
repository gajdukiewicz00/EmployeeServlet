package com.example.EmployeeApp.dao;

import com.example.EmployeeApp.connection.ConnectionPoolInterface;
import com.example.EmployeeApp.model.LogIn;
import com.example.EmployeeApp.security.BCryptHashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInDao {
    private final ConnectionPoolInterface connectionPool;
    private static final Logger logger = LogManager.getLogger(LogInDao.class);
    private BCryptHashing bCryptHashing;

    public LogInDao(ConnectionPoolInterface connectionPool) {
        this.connectionPool = connectionPool;
    }

    public boolean checkCredential(LogIn login) throws SQLException, ClassNotFoundException, InterruptedException {
        String sql = "SELECT * FROM login WHERE username=?";

        Connection con = connectionPool.getConnection();
        con.createStatement();
        bCryptHashing = new BCryptHashing();
        System.out.println("Verify credential.....");
        try (PreparedStatement ps = con.prepareStatement(sql)
        ){

            ps.setString(1, login.getUsername());
//            ps.setString(2, login.getPassword());
            logger.debug("Executing SQL: " + sql);
            ResultSet rs = ps.executeQuery();


            if (rs.next()){
                System.out.println("I ame here");
                System.out.println( rs.getString(1));
                System.out.println("login.getPassword() = " + login.getPassword());
                System.out.println("rs.getString(password) = " + rs.getString("password"));

                if (bCryptHashing.verifyPassword(login.getPassword(), rs.getString("password"))){
                    System.out.println( rs.getString("password"));
                    return true;

                }else {
                }
            }
        } finally {
            connectionPool.releaseConnection(con);
        }

        return false;
    }
}
