/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="amPmCombo"
    private ComboBox amPmCombo; // Value injected by FXMLLoader

    @FXML // fx:id="med1Table"
    private TableView<String> medTable; // Value injected by FXMLLoader
    
    @FXML
    private TableColumn med1Column;

    @FXML // fx:id="addRemButton"
    private Button addRemButton; // Value injected by FXMLLoader

    @FXML // fx:id="hourCombo"
    private ComboBox hourCombo; // Value injected by FXMLLoader
    
    @FXML // fx:id="removeButton"
    private Button removeButton; // Value injected by FXMLLoader

    @FXML
    void addReminder(ActionEvent event) 
    {
    	medTable.setItems(FXCollections.observableArrayList("6:00 AM"));
    }
    
    @FXML
    void removeReminder(ActionEvent event) 
    {
    	medTable.getItems().removeAll(medTable.getItems());
    }

    @FXML
    void loadHome(ActionEvent event) throws IOException 
    {
    	 Stage stage; 
         Parent root;
         
         stage = (Stage) loginButton.getScene().getWindow();
         root = FXMLLoader.load(getClass().getResource("Main.fxml"));
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	
    }
    
}
