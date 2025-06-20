package com.example.garage.service;

import com.example.garage.service.DatabaseService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseServiceImpl implements DatabaseService {
    private static final String URL = "jdbc:mysql://localhost:3306/garage";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private Connection connection;

    @Override
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
