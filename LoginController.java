package com.example.garage.utils;

import com.example.garage.models.Client;
import com.example.garage.models.Mecano;
import com.example.garage.service.AuthService;
import com.example.garage.service.AuthServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private Button loginBtn2;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Text titleText;
    @FXML private Button logoutBtn;
    @FXML private Button loginBtn1;
    @FXML private Button loginBtn;



    private boolean isClientLogin;
    public static Client client;
    private final AuthService authService = new AuthServiceImpl();

    public void setIsClientLogin(boolean isClientLogin) {
        this.isClientLogin = isClientLogin;
        updateTitle();
    }

    private void updateTitle() {
        titleText.setText(isClientLogin ? "CONNEXION CLIENT" : "CONNEXION MECANO");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs");
            return;
        }

        if (isClientLogin) {
            authenticateClient(email, password, event);
        } else {
            authenticateMecano(email, password, event);
        }
    }

    private void authenticateClient(String email, String password, ActionEvent event) {
        try {
            client = authService.authenticateClient(email, password);
            if (client != null) {
                showAlert("Succès", "Connexion réussie en tant que client: " + client.getNom());
                SceneUtils.changeScene(loginBtn, "/com/example/garage/menuclient.fxml");
            } else {
                showAlert("Erreur", "Email ou mot de passe incorrect");
            }
        } catch (Exception e) {
            handleAuthError(e);
        }
    }

    private void authenticateMecano(String email, String password, ActionEvent event) {
        try {
            Mecano mecano = authService.authenticateMecano(email, password);
            if (mecano != null) {
                showAlert("Succès", "Connexion réussie en tant que mécanicien: " + mecano.getNom());
                SceneUtils.changeScene(loginBtn1, "/com/example/garage/menumecano.fxml");
            } else {
                showAlert("Erreur", "Email ou mot de passe incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleAuthError(e);
        }
    }

    private void handleAuthError(Exception e) {
        showAlert("Erreur", "Une erreur est survenue lors de la connexion");
        e.printStackTrace();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        SceneUtils.changeScene(loginBtn, "/com/example/garage/main.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}