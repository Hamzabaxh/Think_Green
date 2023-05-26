package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Connection connection;

    public void initialize() {
        connection = application.connection.getCn();
    }

    public void authenticate() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isAuthenticated = authenticateUser(username, password);

        if (isAuthenticated) {
            // Authentification réussie
            showInfoAlert("Authentification réussie !");
            openStatPage();
        } else {
            // Authentification échouée
            showErrorAlert("Authentification échouée ! Veuillez vérifier vos informations d'identification.");
        }

        // Effacer les champs d'entrée
        usernameField.clear();
        passwordField.clear();
    }

    private boolean authenticateUser(String username, String password) {
        // Préparer la requête SQL pour récupérer les informations d'utilisateur correspondantes
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            // Créer une requête préparée avec les paramètres d'authentification
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Exécuter la requête et récupérer le résultat
            ResultSet resultSet = statement.executeQuery();

            // Vérifier si le résultat contient des lignes
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // L'authentification a échoué
        return false;
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openStatPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec la page "Stat.fxml"
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle
            Stage stage = (Stage) usernameField.getScene().getWindow();

            // Définir la nouvelle scène sur la fenêtre actuelle
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
