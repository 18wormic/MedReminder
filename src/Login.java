import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application
{
	protected static Stage mainStage = null;
	
	private static ObservableList<Time> times;
	
	public static void main(String[] args)
	{
		launch(args);
//		times.add(new Time(7, true));
	}

	@Override
	public void start(Stage stage) throws Exception
	{	
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		
		Scene scene = new Scene(root, 800, 600);
		
		stage.setTitle("Medication Reminder");
		stage.setScene(scene);
		stage.show();
	}

}
