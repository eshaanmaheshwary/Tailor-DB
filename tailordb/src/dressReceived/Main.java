package dressReceived;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application 
{
	
 public void start(Stage primaryStage) 
   {
		try {
				Parent root=(Parent)FXMLLoader.load(getClass().getResource("DressReceived.fxml")); 
				Scene scene = new Scene(root,700,400);
				primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images (1).png")));
				primaryStage.setScene(scene);
				primaryStage.show();
				primaryStage.setTitle("Bangalore Tailoring");
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

