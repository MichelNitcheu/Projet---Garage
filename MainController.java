package com.example.garage.utils;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Button clientBtn;

    @FXML
    private Button mecanoBtn;

    private boolean isClientLogin = false;

    @FXML
    private void handleClientButton() {
        isClientLogin = true;
        loadLoginScreen();
    }

    @FXML
    private void handleMecanoButton() {
        isClientLogin = false;
        loadLoginScreen1();
    }

    private void loadLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/garage/loginclient.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setIsClientLogin(isClientLogin);

            Stage stage = (Stage) clientBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadLoginScreen1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/garage/loginmecano.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setIsClientLogin(isClientLogin);

            Stage stage = (Stage) mecanoBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void register(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/garage/register.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) clientBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
