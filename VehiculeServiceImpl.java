package com.example.garage.service;

import com.example.garage.models.Vehicule;
import com.example.garage.models.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculeServiceImpl implements VehiculeService {
    private DatabaseService databaseService = new DatabaseServiceImpl();

    @Override
    public void ajouterVehicule(Vehicule vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Véhicule ne peut pas être null");
        }

        String query = "INSERT INTO vehicule (marque, modele, annee, immatriculation, client_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, vehicule.getMarque());
            stmt.setString(2, vehicule.getModele());
            stmt.setInt(3, vehicule.getAnnee());
            stmt.setString(4, vehicule.getImmatriculation());
            stmt.setInt(5, vehicule.getClientId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vehicule.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du véhicule", e);
        }
    }

    @Override
    public List<Vehicule> getVehiculesByClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client ne peut pas être null");
        }

        List<Vehicule> vehicules = new ArrayList<>();
        String query = "SELECT * FROM vehicule WHERE client_id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, client.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicules.add(new Vehicule(
                        rs.getInt("id"),
                        rs.getString("marque"),
                        rs.getString("modele"),
                        rs.getInt("annee"),
                        rs.getString("immatriculation"),
                        rs.getInt("client_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des véhicules", e);
        }

        return vehicules;
    }

    @Override
    public Vehicule getVehiculeById(int id) {
        String query = "SELECT * FROM vehicule WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vehicule(
                        rs.getInt("id"),
                        rs.getString("marque"),
                        rs.getString("modele"),
                        rs.getInt("annee"),
                        rs.getString("immatriculation"),
                        rs.getInt("client_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du véhicule", e);
        }

        return null;
    }

    @Override
    public void supprimerVehicule(int id) {
        String query = "DELETE FROM vehicule WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du véhicule", e);
        }
    }
}