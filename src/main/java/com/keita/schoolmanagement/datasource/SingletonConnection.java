package com.keita.schoolmanagement.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SingletonConnection {

    private static SingletonConnection instance;
    private Connection connection;
    private String userName = "developer";
    private String password = "!2Mohamed";
    private String url = "jdbc:mysql://localhost:3306/Schoolmanagement";


    private SingletonConnection () throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, userName, password);
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Connection getConnection () {return connection;}

    public static SingletonConnection getInstance() throws SQLException {
        if (instance == null) {
            return instance = new SingletonConnection();
        }else if (instance.getConnection().isClosed()) {
            instance = new SingletonConnection();
        }
        return instance;
    }

    public static void closeResource(Connection connection, PreparedStatement statement) {
        try {
            if (!statement.isClosed() || !connection.isClosed()) {
                statement.close();
                connection.close();
            }
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}
