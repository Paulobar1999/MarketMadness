package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
/**
 * @author Paul O'Bar, Julian Negrete, Gideon Reyes, Tinesha Johnson, Stephen Otten
 * @version 1.0.0 Build 1 November 20, 2020.
 *
 *  <h1>Program Main Description</h1><br>
 *
 *  Usage :% java Main <br><br>
 *
 *	This program sets the stage and launches the Market Madness JavaFX application. <br><br>
 *
 *	<h1>ALGORITHM:</h1>
 * 	Create the current stage being displayed<br><br>
 *
 *	start() <br>
 *	Starts the program, setting the icon, primaryStage, Title, and launches the main menu<br><br>
 *
 *	main()<br>
 *	This is the main routine required for ALL Java console programs<br>
 *	Create launch statement<br>
 */
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

	/** Description of main(String[] args)
	 *
	 * @param args		String[] args
	 * This is the main routine required for ALL Java console programs
	 *
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
