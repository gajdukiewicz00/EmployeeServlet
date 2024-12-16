package com.example.EmployeeApp.connection;

import java.sql.Connection;

public interface ConnectionPoolInterface {
    Connection getConnection() throws InterruptedException;
    void releaseConnection(Connection connection);
}
