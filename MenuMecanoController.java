package com.example.garage.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.example.garage.models.Mecano;
import com.example.garage.service.RendezVousService;
import com.example.garage.service.RendezVousServiceImpl;

import java.io.IOException;

public class MenuMecanoController {
    @FXML private Label welcomeLabel;
    @FXML private Button Deconnexion;
    @FXML private Button btnReparationmecano;
    @FXML private Button btnterminer;
    @FXML private Button btnentretienmecano;
    @FXML private Button btntentretienmecano;
    @FXML private StackPane contentPane;

    private Mecano mecano;
    private RendezVousService rendezVousService;

    public void initialize() {
        // Initialisation des services
        this.rendezVousService = new RendezVousServiceImpl();

        // Style des boutons
        String buttonStyle = "-fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 5;";
        btnReparationmecano.setStyle(buttonStyle + "-fx-background-color: #27ae60;");
        btnterminer.setStyle(buttonStyle + "-fx-background-color: #e67e22;");
        btnentretienmecano.setStyle(buttonStyle + "-fx-background-color: #3498db;");
        btntentretienmecano.setStyle(buttonStyle + "-fx-background-color: #9b59b6;");
    }

    public void setMecano(Mecano mecano) {
        this.mecano = mecano;
        welcomeLabel.setText("BLURMOTOR - " + mecano.getPrenom() + " " + mecano.getNom());
    }

    @FXML
    private void handleReparationmecano() {
    }

    @FXML
    private void handleterminer() {

    }

    @FXML
    private void handleentretienmecano() {
    }

    @FXML
    private void handletentretienmecano() {
    }

    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/garage/main.fxml"));
            Stage stage = (Stage) Deconnexion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (IOException e) {
            showError("Erreur", "Impossible de se déconnecter");
            e.printStackTrace();
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}