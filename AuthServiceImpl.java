package com.example.garage.service;

import com.example.garage.models.Client;
import com.example.garage.models.Mecano;
import java.sql.*;

public class AuthServiceImpl implements AuthService {
    private DatabaseService databaseService = new DatabaseServiceImpl();

    @Override
    public Client authenticateClient(String email, String password) {
        String query = "SELECT * FROM clients WHERE email = ? AND password = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password); // Mot de passe en clair
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Mecano authenticateMecano(String email, String password) {
        String query = "SELECT * FROM mecanos WHERE email = ? AND password = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password); // Mot de passe en clair
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Mecano(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("specialite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}