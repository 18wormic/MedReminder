import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController
{
	
	private String workingDirectory;
	private String inFolder;

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

	@FXML
	private Text title;

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
			Login.setUser((User) new ObjectInputStream(new FileInputStream(inFolder + usernameField.getText() + ".dat"))
					.readObject());
			System.out.println(Login.getUser().getPass());
			System.out.println(Login.getUser().getUser());
			if (Login.getUser().getPass().equals(passwordField.getText()))
			{
				Stage stage;
				Parent root;

				stage = (Stage) loginButton.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("Main.fxml"));

				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

				// for (Medication med : Login.getUser().getMed())
				// {
				// Tab temp = new Tab();
				//
				// }
			}
			else
			{
				loginStatusText.setText("Wrong password.");
				Login.setUser(null);
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
		
		Login.setWorkingDirectory(workingDirectory);
		Login.setFolder(inFolder);
	}

}