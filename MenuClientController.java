package com.example.garage.utils;

import com.example.garage.models.Client;
import com.example.garage.service.RendezVousService;
import com.example.garage.service.VehiculeService;
import com.example.garage.service.VehiculeServiceImpl;
import com.example.garage.service.RendezVousServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuClientController {
    @FXML private Button btnReparation;
    @FXML private Button btnEntretien;
    @FXML private Label welcomeLabel;

    public  VehiculeService vehiculeService;
    private RendezVousService rendezVousService;

    public void initialize(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client ne peut pas être null");
        }

        welcomeLabel.setText("Bienvenue, " + client.getPrenom() + " " + client.getNom());

        // Initialiser les services
        this.vehiculeService = new VehiculeServiceImpl();
        this.rendezVousService = new RendezVousServiceImpl();
    }

    @FXML
    private void handleReparation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/garage/reparation.fxml"));
            Parent root = loader.load();

            // Vérifier que les services sont bien initialisés
            if (vehiculeService == null) {
                vehiculeService = new VehiculeServiceImpl();
            }
            if (rendezVousService == null) {
                rendezVousService = new RendezVousServiceImpl();
            }

            ReparationController controller = loader.getController();
            if (controller == null) {
                throw new IllegalStateException("Le contrôleur ReparationController n'a pas pu être initialisé");
            }

            controller.initialize(LoginController.client, vehiculeService, rendezVousService);

            Stage stage = (Stage) btnReparation.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (IOException e) {
            SceneUtils.showError("Erreur", "Impossible de charger la page de réparation: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            SceneUtils.showError("Erreur", "Erreur inattendue: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEntretien() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/garage/entretien.fxml"));
            Parent root = loader.load();

            EntretienController controller = loader.getController();
            controller.initialize(LoginController.client, vehiculeService, rendezVousService);

            Stage stage = (Stage) btnEntretien.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (IOException e) {
            SceneUtils.showError("Erreur", "Impossible de charger la page d'entretien");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/garage/main.fxml"));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (IOException e) {
            SceneUtils.showError("Erreur", "Impossible de charger la page principale");
            e.printStackTrace();
        }
    }
}