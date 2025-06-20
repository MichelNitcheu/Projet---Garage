package com.example.garage.utils;

import com.example.garage.models.Client;
import com.example.garage.service.RendezVousService;
import com.example.garage.service.VehiculeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EntretienController {
    @FXML private ComboBox<String> typeEntretienComboBox;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> heureComboBox;
    @FXML private TextArea notesField;

    private Client client;
    private VehiculeService vehiculeService;
    private RendezVousService rendezVousService;

    public void initialize(Client client, VehiculeService vehiculeService, RendezVousService rendezVousService) {
        this.client = client;
        this.vehiculeService = vehiculeService;
        this.rendezVousService = rendezVousService;

        // Initialisation des contrôles
        initializeControls();
    }

    private void initializeControls() {
        // Types d'entretien
        typeEntretienComboBox.getItems().addAll(
                "Vidange moteur",
                "Changement des filtres",
                "Contrôle technique",
                "Révision générale",
                "Changement des plaquettes de frein",
                "Changement des pneus",
                "Entretien climatisation"
        );

        // Heures disponibles
        heureComboBox.getItems().addAll(
                "08:00", "09:00", "10:00", "11:00",
                "13:00", "14:00", "15:00", "16:00"
        );

        // Date par défaut (aujourd'hui)
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleConfirmer() {
        if (!validateForm()) {
            return;
        }

        try {
            // Enregistrement de la demande d'entretien
            enregistrerEntretien();

            // Retour au menu principal
            retourAuMenu();
        } catch (Exception e) {
            SceneUtils.showError("Erreur", "Une erreur est survenue lors de l'enregistrement");
            e.printStackTrace();
        }
    }

    private boolean validateForm() {
        if (typeEntretienComboBox.getValue() == null) {
            SceneUtils.showError("Erreur", "Veuillez sélectionner un type d'entretien");
            return false;
        }

        if (datePicker.getValue() == null) {
            SceneUtils.showError("Erreur", "Veuillez sélectionner une date");
            return false;
        }

        if (heureComboBox.getValue() == null) {
            SceneUtils.showError("Erreur", "Veuillez sélectionner une heure");
            return false;
        }

        return true;
    }

    private void enregistrerEntretien() {
        String typeEntretien = typeEntretienComboBox.getValue();
        LocalDate date = datePicker.getValue();
        LocalTime heure = LocalTime.parse(heureComboBox.getValue());
        String notes = notesField.getText();

        // Ici vous pourriez ajouter la logique pour enregistrer en base de données
        // Par exemple :
        // Entretien entretien = new Entretien(typeEntretien, date, heure, notes);
        // entretienService.enregistrerEntretien(entretien);

        SceneUtils.showInfo("Succès", "Votre demande d'entretien a été enregistrée");
    }

    private void retourAuMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/garage/menuclient.fxml"));
        Parent root = loader.load();

        MenuClientController controller = loader.getController();
        controller.initialize(client);

        Stage stage = (Stage) notesField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleAnnuler() {
        try {
            retourAuMenu();
        } catch (IOException e) {
            SceneUtils.showError("Erreur", "Impossible de retourner au menu");
            e.printStackTrace();
        }
    }
}