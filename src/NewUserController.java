import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewUserController
{
	
	private String workingDirectory;
	private String inFolder;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField newUserUsernameField;

	@FXML
	private Button backButton;

	@FXML
	private TextField newUserPasswordField2;

	@FXML
	private Text newUserStatusText;

	@FXML
	private Button createUserButton;

	@FXML
	private Text title;

	@FXML
	private TextField newUserPasswordField;

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
			Login.setUser(new User(newUserUsernameField.getText(), newUserPasswordField.getText()));
			
			ArrayList<Medication> medArray = Login.getUser().getMed();
			Medication tempMed = new Medication("Enter name");
			medArray.add(tempMed);

			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(new File(inFolder + newUserUsernameField.getText() + ".dat")));
			oos.writeObject(Login.getUser());
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
	void initialize()
	{
		workingDirectory = Login.getWorkingDirectory();
		inFolder = Login.getFolder();
	}
}