
/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController
{

	private User currentUser;

	@FXML
	private TextField usernameField;

	@FXML
	private Text loginStatusText;

	@FXML
	private Button loginButton;

	@FXML
	private TextField passwordField;

	@FXML
	private Button newUserButton;

	private String workingDirectory;

	private String inFolder;

	@FXML
	void login(ActionEvent event) throws Exception
	{
		File folder = new File(workingDirectory);
		boolean found = false;
		for (String fileName : folder.list())
		{
			if (fileName.equals(usernameField.getText() + ".dat"))
			{
				found = true;
				break;
			}
		}

		if (found)
		{
			currentUser = (User) new ObjectInputStream(new FileInputStream(inFolder + usernameField.getText() + ".dat"))
					.readObject();

			if (currentUser.getPass().equals(passwordField.getText()))
			{
				loadHome(event);
			}
			else
			{
				loginStatusText.setText("Wrong password.");
				currentUser = null;
			}
		}
		else
		{
			loginStatusText.setText("User does not exist.");
		}

	}

	@FXML
	void newUser(ActionEvent event) throws IOException
	{
		Stage stage;
		Parent root;

		stage = (Stage) loginButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("NewUser.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private TextField newUserUsernameField;

	@FXML
	private TextField newUserPasswordField2;

	@FXML
	private Text newUserStatusText;

	@FXML
	private Button createUserButton;

	@FXML
	private TextField newUserPasswordField;
	
	@FXML
	private Button backButton;
	
	@FXML
	void goBack(ActionEvent event) throws Exception
	{
		Stage stage;
		Parent root;

		stage = (Stage) backButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void createNewUser(ActionEvent event) throws IOException
	{
		boolean found = false;
		File temp = new File(workingDirectory);
		String[] fileList = temp.list();

		if (!(fileList.length == 0))
		{
			for (String fileName : temp.list())
			{
				if (fileName.equals(newUserUsernameField.getText() + ".dat"))
				{
					found = true;
				}
			}

			if (found)
			{
				newUserStatusText.setText("Username already in use.");
				return;
			}
		}

		if (newUserPasswordField.getText().equals(newUserPasswordField2.getText()))
		{
			currentUser = new User(newUserUsernameField.getText(), newUserPasswordField.getText());
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(new File(inFolder + newUserUsernameField.getText() + ".dat")));
			oos.writeObject(currentUser);
			oos.close();

			Stage stage;
			Parent root;

			stage = (Stage) createUserButton.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("Main.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			newUserStatusText.setText("Passwords do not match.");
		}
	}

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
	private TableColumn<?, ?> med1Column;

	@FXML
	private TableView<?> medTable;

	@FXML
	private Button removeMedicationButton;

	@FXML
	private Button addRemButton;

	@FXML
	private ComboBox<?> hourCombo;
	
	@FXML
	private Button logoutButton;
	
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
		
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(
						new File(inFolder + currentUser.getUser() + ".dat")));
		oos.writeObject(currentUser);
		oos.close();
		
		currentUser = null;
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

	private ObservableList<Time> times;

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
		String OS = (System.getProperty("os.name")).toUpperCase();

		if (OS.contains("WIN"))
		{
			workingDirectory = System.getenv("AppData");
			File dataFolder = new File(workingDirectory + "\\MedReminder");

			if (dataFolder.exists())
			{
				workingDirectory += "\\MedReminder";
			}
			else
			{
				dataFolder.mkdir();
				workingDirectory += "\\MedReminder";
			}

			inFolder = workingDirectory + "\\";
		}
		else
		{
			workingDirectory = System.getProperty("user.home");
			workingDirectory += "/Library/Application Support";

			File dataFolder = new File(workingDirectory + "/MedReminder");

			if (dataFolder.exists())
			{
				workingDirectory += "/MedReminder";
			}
			else
			{
				dataFolder.mkdir();
				workingDirectory += "/MedReminder";
			}

			inFolder = workingDirectory + "/";
		}
	}

}
