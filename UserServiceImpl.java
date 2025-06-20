package com.example.garage.service;

import com.example.garage.models.Client;
import com.example.garage.service.DatabaseService;
import java.sql.*;

public class UserServiceImpl implements UserService {

    @Override
    public boolean registerUser(Client user) {
        String sql = "INSERT INTO clients (nom, prenom, telephone, email, password) VALUES (?, ?, ?, ?, ?)";

        DatabaseService databaseService = new DatabaseServiceImpl();

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.setString(3, user.getTelephone());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription: " + e.getMessage());
        }
        return false;
    }
}
