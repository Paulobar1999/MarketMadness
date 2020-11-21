package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	/**
	 * the current stage being displayed
	 */
	public static Stage primaryStage;

	/**
	 * start, starts the program, setting the icon, primaryStage, Title, and launches the main menu  
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root, 841, 731);
			scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
			Main.primaryStage = primaryStage;
			primaryStage.setTitle("MarketMadness");
			primaryStage.getIcons().add(new Image("file:icon.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
