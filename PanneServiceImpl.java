package com.example.garage.service;

import com.example.garage.models.Panne;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanneServiceImpl implements PanneService {
    private DatabaseService databaseService = new DatabaseServiceImpl();

    @Override
    public List<Panne> getAllPannes() {
        List<Panne> pannes = new ArrayList<>();
        String query = "SELECT * FROM panne";

        try (Connection conn = databaseService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                pannes.add(new Panne(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des pannes", e);
        }

        return pannes;
    }

    @Override
    public Panne getPanneById(int id) {
        String query = "SELECT * FROM panne WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Panne(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la panne", e);
        }

        return null;
    }

    @Override
    public List<Panne> getPannesByNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            return getAllPannes();
        }

        List<Panne> pannes = new ArrayList<>();
        String query = "SELECT * FROM panne WHERE nom LIKE ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + nom + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pannes.add(new Panne(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche des pannes", e);
        }

        return pannes;
    }
}