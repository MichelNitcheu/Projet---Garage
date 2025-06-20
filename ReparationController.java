package com.example.garage.utils;

import com.example.garage.models.*;
import com.example.garage.service.RendezVousService;
import com.example.garage.service.VehiculeService;
import com.example.garage.service.PanneService;
import com.example.garage.service.PanneServiceImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ReparationController {
    @FXML private ComboBox<Vehicule> vehiculeComboBox;
    @FXML private TextArea descriptionField;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> heureComboBox;
    @FXML private ComboBox<Panne> panneComboBox;
    @FXML private Button btnConfirmer;

    private VehiculeService vehiculeService;
    private RendezVousService rendezVousService;
    private PanneService panneService;

    public void initialize(Client client, VehiculeService vehiculeService, RendezVousService rendezVousService) {
        try {
            if (client == null) {
                throw new IllegalArgumentException("Le client ne peut pas être null");
            }
            if (vehiculeService == null) {
                throw new IllegalArgumentException("Le service véhicule ne peut pas être null");
            }
            if (rendezVousService == null) {
                throw new IllegalArgumentException("Le service rendez-vous ne peut pas être null");
            }

            this.vehiculeService = vehiculeService;
            this.rendezVousService = rendezVousService;
            this.panneService = new PanneServiceImpl();

            Platform.runLater(() -> {
                initVehiculeComboBox();
                initHeureComboBox();
                initPanneComboBox();
                datePicker.setValue(LocalDate.now());
            });
        } catch (Exception e) {
            SceneUtils.showError("Erreur d'initialisation", e.getMessage());
            e.printStackTrace();
        }
    }

    private void initVehiculeComboBox() {
        try {
            List<Vehicule> vehicules = vehiculeService.getVehiculesByClient(LoginController.client);

            // Debug: Affichez les véhicules récupérés
            System.out.println("Véhicules trouvés: " + vehicules);

            if (vehicules == null || vehicules.isEmpty()) {
                SceneUtils.showWarning("Avertissement", "Aucun véhicule enregistré pour ce client");
                return;
            }

            // 1. Créez la liste observable
            ObservableList<Vehicule> observableList = FXCollections.observableArrayList(vehicules);

            // 2. Configurez le ComboBox
            vehiculeComboBox.setItems(observableList);

            // 3. Définissez le StringConverter
            vehiculeComboBox.setConverter(new StringConverter<Vehicule>() {
                @Override
                public String toString(Vehicule vehicule) {
                    return vehicule != null ? vehicule.getMarque() + " " + vehicule.getModele() : "";
                }

                @Override
                public Vehicule fromString(String string) {
                    // Pas nécessaire pour un affichage simple
                    return null;
                }
            });

            // 4. Sélectionnez le premier élément si disponible
            if (!vehicules.isEmpty()) {
                Platform.runLater(() -> {
                    vehiculeComboBox.getSelectionModel().selectFirst();
                });
            }

        } catch (Exception e) {
            SceneUtils.showError("Erreur", "Impossible de charger les véhicules: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initHeureComboBox() {
        // Heures d'ouverture du garage (9h-17h)
        List<String> heures = List.of(
                "09:00", "10:00", "11:00", "12:00",
                "13:00", "14:00", "15:00", "16:00"
        );
        heureComboBox.setItems(FXCollections.observableArrayList(heures));
        heureComboBox.getSelectionModel().selectFirst();
    }

    private void initPanneComboBox() {
        try {
            List<Panne> pannes = panneService.getAllPannes();

            // Debug
            System.out.println("Pannes trouvées: " + pannes);

            if (pannes == null || pannes.isEmpty()) {
                SceneUtils.showWarning("Avertissement", "Aucune panne disponible");
                return;
            }

            // 1. Configuration du ComboBox
            panneComboBox.setItems(FXCollections.observableArrayList(pannes));

            // 2. StringConverter pour l'affichage
            panneComboBox.setConverter(new StringConverter<Panne>() {
                @Override
                public String toString(Panne panne) {
                    return panne != null ? panne.getNom() + " (" + panne.getPrix() + "€)" : "";
                }

                @Override
                public Panne fromString(String string) {
                    return null; // Non nécessaire en lecture seule
                }
            });

            // 3. Sélection auto du premier élément
            Platform.runLater(() -> {
                panneComboBox.getSelectionModel().selectFirst();
            });

        } catch (Exception e) {
            SceneUtils.showError("Erreur", "Impossible de charger les pannes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleConfirmer() {
        try {
            Vehicule vehicule = vehiculeComboBox.getValue();
            String description = descriptionField.getText();
            LocalDate date = datePicker.getValue();
            String heure = heureComboBox.getValue();
            Panne panne = panneComboBox.getValue();

            if (vehicule == null || date == null || heure == null) {
                SceneUtils.showError("Erreur", "Veuillez remplir tous les champs obligatoires");
                return;
            }

            // Créer la date/heure du rendez-vous
            LocalTime time = LocalTime.parse(heure);
            LocalDateTime dateTime = LocalDateTime.of(date, time);

            // Créer le rendez-vous
            RendezVous rendezVous = new RendezVous(
                    0, // ID sera généré par la base de données
                    dateTime,
                    "planifie",
                    vehicule,
                    panne
            );
            //rendezVous.setDescription(description);

            // Enregistrer le rendez-vous
            rendezVousService.creerRendezVous(rendezVous);

            SceneUtils.showInfo("Succès", "Votre demande de réparation a été enregistrée");

            // Retour au menu
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/garage/menuclient.fxml"));
            Parent root = loader.load();
            MenuClientController controller = loader.getController();
            controller.initialize(LoginController.client);

            Stage stage = (Stage) btnConfirmer.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();

        } catch (Exception e) {
            SceneUtils.showError("Erreur", "Une erreur est survenue lors de l'enregistrement: " + e.getMessage());
            e.printStackTrace();
        }
    }
}