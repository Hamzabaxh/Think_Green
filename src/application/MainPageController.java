package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPageController implements Initializable {
	@FXML 
	private BorderPane bp  ;
	
	
	@FXML 
	public void getToDashboard(ActionEvent e ) {
		LoadPage("Dashboard");
	}
	
	@FXML 
	public void getToDetails(ActionEvent e ) {
		LoadPage("details");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LoadPage("Dashboard");
	} 
	
	private void LoadPage(String page ) {
		Parent root = null ;
		try {
			root = FXMLLoader.load(getClass().getResource(page+".FXML"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		bp.setCenter(root);
		
	}
	@FXML
	public void openLoginPage(ActionEvent ee) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("authentification.fxml"));
	            Parent root = loader.load();

	            Scene scene = new Scene(root);

	            Stage stage = (Stage) bp.getScene().getWindow();

	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
}
