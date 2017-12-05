
/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController
{

	private String workingDirectory;

	private String inFolder;

	@FXML
	private ComboBox<?> amPmCombo;

	@FXML
	private String time1;

	@FXML
	private TextField medName;

	@FXML
	private String time2;

	@FXML
	private ComboBox<?> dayCombo;

	@FXML
	private Button addMedicationButton;

	@FXML
	private TableColumn<Medication, String> medColumn;

	@FXML
	private TableColumn<Medication, String> timeColumn;

	@FXML
	private TableColumn<Medication, String> descColumn;

	@FXML
	private TableView<Medication> medTable;

	@FXML
	private Button removeMedicationButton;

	@FXML
	private Button addRemButton;

	@FXML
	private ComboBox<?> hourCombo;

	@FXML
	private Button logoutButton;
	
	@FXML
	private TabPane medTabs;
	
//	private ObservableList<Medication> medList = FXCollections.observableArrayList();

	@FXML
	void logout(ActionEvent event) throws Exception
	{

		Stage stage;
		Parent root;

		stage = (Stage) logoutButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		System.out.println(Login.getUser().getPass());
		System.out.println(Login.getUser().getUser());
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File(inFolder + Login.getUser().getUser() + ".dat")));
		oos.writeObject(Login.getUser());
		oos.close();

		Login.setUser(null);
	}

	@FXML
	void addReminder(ActionEvent event)
	{

	}

	@FXML
	void addMed(ActionEvent event)
	{

	}

	@FXML
	void removeMed(ActionEvent event)
	{

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize()
	{
		
//		medColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//		descColumn.setCellValueFactory(cellData -> cellData.getValue().descProperty());
		
		medColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("name"));
		
		medTable.setItems(FXCollections.observableArrayList(Login.getUser().getMed()));
	}

}
