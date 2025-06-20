package com.example.garage.utils;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.garage.models.Client;
import com.example.garage.service.UserService;
import com.example.garage.service.UserServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    private final UserService userService = new UserServiceImpl();

    @FXML private TextField nameField;
    @FXML private TextField lastnameField;// Modifié (anciennement nomField)
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField phoneField;     // Modifié (anciennement telField)
    @FXML private Button registerButton;
    @FXML private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation
    }

    @FXML
    private void handleRegister() {
        if (!validateFields()) {
            return;
        }

        Client user = new Client();
        user.setPrenom(lastnameField.getText());
        user.setNom(nameField.getText());      // Utilise nameField
        user.setEmail(emailField.getText());
        user.setPassword(passwordField.getText());
        user.setTelephone(phoneField.getText());     // Utilise phoneField

        if (userService.registerUser(user)) {
            SceneUtils.showInfo("Succès", "Inscription réussie!\nCliquez sur Ok pour aller à la page d'accueil");
            loadHomePage();
        } else {
            SceneUtils.showError("Erreur", "L'inscription a échoué");
        }
    }

    @FXML
    private void handleBack() throws IOException  {
        SceneUtils.changeScene(backButton, "/com/example/garage/main.fxml");
    }

    private void loadHomePage() {
        try {
            Parent root = SceneUtils.loadFXML("/com/example/garage/main.fxml");
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            SceneUtils.showError("Erreur", "Impossible de charger la page d'accueil: " + e.getMessage());
        }
    }

    private boolean validateFields() {
        if (nameField.getText().isEmpty() ||
                lastnameField.getText().isEmpty() || // Modifié (nameField)
                emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                confirmPasswordField.getText().isEmpty()) {

            SceneUtils.showError("Erreur", "Veuillez remplir tous les champs obligatoires");
            return false;
        }

        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            SceneUtils.showError("Erreur", "Les mots de passe ne correspondent pas");
            return false;
        }

        return true;
    }
}