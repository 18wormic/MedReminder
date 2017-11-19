import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class HomePage extends Application{
	
	Stage window;
	
	Scene stage, medications;
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage){
    	Label thislabel = new Label("Welcome!");
    	window = primaryStage;
        window.setTitle("Home");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(e-> window.setScene(medications)); 
        
        VBox layout = new VBox(20);
        layout.getChildren().addAll(thislabel,btn);
        
        stage = new Scene(layout,200,200);
      /*  
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        window.setScene(new Scene(root, 300, 250));
        window.show();*/
    }
}
