package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import entity.Consomation;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class dashboardController implements Initializable{
	
	@FXML
	private Label total ; 
	@FXML 
	private TableView<Consomation> cTable ; 
	@FXML 
	private TableColumn<Consomation, String >  dateCol ;	
	@FXML 
	private TableColumn<Consomation, String >  qteCol ;	
	
	@FXML 
	private TableColumn<Consomation, String >  needCol ;	

	@FXML 
	private TableColumn<Consomation, String > jourCol ;	
	
	public void openAdd(ActionEvent e) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addConsumption.fxml"));
		Parent root;
		try {
			root = loader.load();
			Stage newStage = new Stage();
			newStage.setScene(new Scene(root));

			newStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}
	ObservableList<Consomation> Liste= FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();
		refrechList();
	}
	
	
	private void  loadData() {
		 connection = application.connection.getCn();
		  dateCol.setCellValueFactory(cellData -> {
			    Consomation docteur = cellData.getValue(); 
			    Timestamp num = docteur.getTemp(); 
			    Integer hours = num.toLocalDateTime().getHour();
		        Integer minutes = num.toLocalDateTime().getMinute();
		        
			    return new SimpleObjectProperty<>(hours.toString()+":"+minutes.toString());
			});
		  qteCol.setCellValueFactory(cellData -> {
			    Consomation docteur = cellData.getValue(); 
			    float num = docteur.getConsomation(); 
			  
		        
			    return new SimpleObjectProperty<>(num+" Litres");
			});
		  needCol.setCellValueFactory(cellData -> {
			    Consomation docteur = cellData.getValue(); 
			    String num = docteur.getNeed(); 
			  
		        
			    return new SimpleObjectProperty<>(num);
			});
		  jourCol.setCellValueFactory(cellData -> {
			    Consomation docteur = cellData.getValue(); 
			    Timestamp num = docteur.getTemp(); 
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate = dateFormat.format(num);
			  
		        
			    return new SimpleObjectProperty<>(formattedDate);
			});
		  
	}
	private Connection connection = null ;
	private String query= "" ;
	private ResultSet resultSet = null ;
	Consomation con =null ; 


	java.sql.PreparedStatement preparedStatement = null ;
 
	@FXML
	public void refrechList() {
		Liste.clear();
		query= "Select * from consomation";
		try {
			float t = 0 ; 
			preparedStatement  = connection.prepareStatement(query) ;
			resultSet = preparedStatement.executeQuery() ;
			while(resultSet.next()) {
			

				con = new Consomation(
						resultSet.getInt("id"), 
						resultSet.getString("need"),
						resultSet.getTimestamp("dateC"),
						resultSet.getFloat("quantity"),
						resultSet.getString("username")
						);
				t+= con.getConsomation();
				Liste.add(con);
						System.out.print(con.toString());
			}
			total.setText(String.valueOf(t) + "Litres");
			cTable.setItems(Liste);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	@FXML 
	public void delete(ActionEvent e) {
		Consomation selectedItem = cTable.getSelectionModel().getSelectedItem();
		query = " delete from consomation where id = " + selectedItem.getId(); 
		try {
			preparedStatement  = connection.prepareStatement(query) ;
			preparedStatement.executeUpdate() ;
			refrechList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
}
