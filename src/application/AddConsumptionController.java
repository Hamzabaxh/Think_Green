package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddConsumptionController {
	@FXML 
	private TextField qtField; 
	@FXML 
	private TextArea needField ; 

private Connection connection = null ;
private String query= "" ;

 

java.sql.PreparedStatement preparedStatement = null ;
	@FXML 
	public void save() {
		connection = application.connection.getCn();
		String qt = qtField.getText(); 
		String need = needField.getText();
		
		if(qt.isEmpty()|| need.isEmpty()) {
			 Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Error");
		        alert.setHeaderText("Error");
		        alert.setContentText("fill the blanks");
		        alert.showAndWait();
		}else {
			query = "INSERT INTO `consomation`( `need`, `username` , `quantity`) VALUES (?,?,?)";
			  try {
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			      preparedStatement.setString(1, need);
			      preparedStatement.setString(2, "hamza");
		            preparedStatement.setString(3, qt);
		            preparedStatement.executeUpdate();
		            

		            Stage currentStage = (Stage) qtField.getScene().getWindow();
					currentStage.close();
		            
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
