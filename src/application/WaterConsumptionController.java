package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WaterConsumptionController {
    @FXML
    private TextField firstQuarterField;

    @FXML
    private TextField secondQuarterField;

    @FXML
    private Button calculateButton;

    @FXML
    private Label resultLabel;

    private double averageConsumption = 100.0; // Consommation moyenne prédéfinie

    public void calculateAverageConsumption() {
        try {
            double firstQuarter = Double.parseDouble(firstQuarterField.getText());
            double secondQuarter = Double.parseDouble(secondQuarterField.getText());

            double average = (firstQuarter + secondQuarter) / 2;

            if (average <= averageConsumption) {
                resultLabel.setText("Votre consommation est inférieure ou égale à la moyenne !"
                        + "\nConseil : Continuez à utiliser l'eau de manière économe pour préserver les ressources.");
            } else {
                resultLabel.setText("Votre consommation est supérieure à la moyenne !"
                        + "\nConseil : Essayez de réduire votre consommation en adoptant des habitudes économes en eau.");
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Veuillez entrer des valeurs valides.");
        }

        firstQuarterField.clear();
        secondQuarterField.clear();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
