
/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController
{
	private static Medication currentMed;

	private String workingDirectory;

	private String inFolder;

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
	private Button logoutButton;

	@FXML
	private TabPane medTabs;

	@FXML
	private Button removeRem;
	
	@FXML
	private Text mainStatus;

	private static TableView tableView;

	private static TabPane tabPane;

	@FXML
	void removeReminder(ActionEvent event)
	{
		Medication toRem = medTable.getSelectionModel().getSelectedItem();

		if (toRem == null)
		{
			mainStatus.setText("No reminder selected.");
		}
		else
		{
			mainStatus.setText("");
			for (Medication med : Login.getUser().getMed())
			{
				if (med.getName().equals(toRem.getName()));
			}
		}
	}

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

		save();

		Login.setUser(null);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() throws Exception
	{

		workingDirectory = Login.getWorkingDirectory();
		inFolder = Login.getFolder();
		tableView = medTable;
		tabPane = medTabs;

		medColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("name"));
		descColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("desc"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("time"));
		refresh();

		for (Medication med : Login.getUser().getMed())
		{
			setCurrentMed(med);

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Tab.fxml"));
			loader.getNamespace().put("name", med.getName());
			loader.getNamespace().put("desc", med.getDescription());

			Tab temp;
			temp = loader.load();

			medTabs.getTabs().add(temp);

		}
	}

	public static Medication getCurrentMed()
	{
		return currentMed;
	}

	public static void setCurrentMed(Medication med)
	{
		currentMed = med;
	}

	public static TabPane getTabPane()
	{
		return tabPane;
	}

	public static TableView getTable()
	{
		return tableView;
	}

	public static void refresh()
	{
		ArrayList<Medication> tempArray = new ArrayList<Medication>();

		for (Medication aMed : Login.getUser().getMed())
		{
			for (Time aTime : aMed.getReminders())
			{
				Medication tempMed = new Medication(aMed.getName());
				tempMed.setDescription(aMed.getDescription());
				tempMed.setTime(aTime);
				tempArray.add(tempMed);
			}
		}
		tableView.setItems(FXCollections.observableArrayList(tempArray));
		tableView.refresh();
	}

	public void save() throws IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File(inFolder + Login.getUser().getUser() + ".dat")));
		oos.writeObject(Login.getUser());
		oos.close();
	}
}
