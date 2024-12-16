package com.example.EmployeeApp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool implements ConnectionPoolInterface{

    private static final String URL = "jdbc:mysql://localhost:3306/phonebookapp";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static volatile ConnectionPool instance;
    private static final Lock lock = new ReentrantLock();
    private final BlockingQueue<Connection> pool;
    private static final int POOL_SIZE = 12;

    public ConnectionPool() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        pool = new ArrayBlockingQueue<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(createConnection());
            System.out.println("Pool size: " + i);
        }

    }

    public static ConnectionPool getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null){
            lock.lock();
            try {
                if (instance == null){
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    @Override
    public void releaseConnection(Connection connection) {
        if (connection != null){
            pool.offer(connection);
        }
    }
}
