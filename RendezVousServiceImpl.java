package com.example.garage.service;

import com.example.garage.models.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.garage.models.*;
import com.example.garage.service.RendezVousService;
import com.example.garage.service.DatabaseService;
import com.example.garage.service.DatabaseServiceImpl;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RendezVousServiceImpl implements RendezVousService {
    private DatabaseService databaseService = new DatabaseServiceImpl();

    @Override
    public void creerRendezVous(RendezVous rendezVous) {
        String query = "INSERT INTO rendezvous (date_heure, statut, vehicule_id, panne_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, Timestamp.valueOf(rendezVous.getDateHeure()));
            stmt.setString(2, rendezVous.getStatut());
            stmt.setInt(3, rendezVous.getVehicule().getId());
            stmt.setObject(4, rendezVous.getPanne() != null ? rendezVous.getPanne().getId() : null, Types.INTEGER);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    rendezVous.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du rendez-vous", e);
        }
    }

    @Override
    public void annulerRendezVous(int id) {
        String query = "UPDATE rendezvous SET statut = 'annule' WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'annulation du rendez-vous", e);
        }
    }

    @Override
    public List<RendezVous> getRendezVousByClient(Client client) {
        List<RendezVous> rendezVousList = new ArrayList<>();
        String query = "SELECT r.* FROM rendezvous r JOIN vehicule v ON r.vehicule_id = v.id WHERE v.client_id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, client.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicule vehicule = new VehiculeServiceImpl().getVehiculeById(rs.getInt("vehicule_id"));
                Panne panne = rs.getObject("panne_id") != null ?
                        new PanneServiceImpl().getPanneById(rs.getInt("panne_id")) : null;

                rendezVousList.add(new RendezVous(
                        rs.getInt("id"),
                        rs.getTimestamp("date_heure").toLocalDateTime(),
                        rs.getString("statut"),
                        vehicule,
                        panne
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des rendez-vous", e);
        }

        return rendezVousList;
    }

    @Override
    public List<RendezVous> getRendezVousByMecano(Mecano mecano) {
        // Implémentation à compléter selon vos besoins
        return new ArrayList<>();
    }
}