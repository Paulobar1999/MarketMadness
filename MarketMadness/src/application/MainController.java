package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * @author Paul O'Bar, Julian Negrete, Gideon Reyes, Tinesha Johnson, Stephen Otten
 * @version 1.0.0 Build 1 November 20, 2020.
 *
 *  <h1>Program MainController Description</h1><br>
 *
 *  Usage :% java MainController <br><br>
 *
 *	This program manipulates the Main Menu of the Market Madness application. It has buttons that will navigate to the start
 *	of the game, the credits screen, and the exit button to exit the game. <br><br>
 *
 *	<h1>ALGORITHM:</h1>
 *	GameStart() <br>
 *	Switches the scene to Game.fxml<br><br>
 *
 *	GoToCredits() <br>
 *	Switches the scene to Credits.fxml<br><br>
 *
 *	BackToMainMenu() <br>
 *	Returns the player to the Main.fxml<br><br>
 *
 *	Exit() <br>
 *	Exits the application<br>
 */
public class MainController {

	@FXML
	private Button playButton;
	@FXML
	private Button creditsButton;
	@FXML
	private Button ExitButton;

	/**
	 * GameStart switches the scene to Game.fxml
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void GameStart(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Game.css").toExternalForm());
		Main.primaryStage.setScene(scene);
	}

	/**
	 * GoToCredits switches the scene to Credits.fxml
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void GoToCredits(ActionEvent event) throws IOException {
		// get 'Credits' button to work.
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Credits.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Credits.css").toExternalForm());

		Main.primaryStage.setScene(scene);
	}
	/**
	 * BackToMainMenu returns the player to the Main.fxml
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void BackToMainMenu(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
		Main.primaryStage.setScene(scene);
	}
	/**
	 *  Exit, exits the application.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void Exit(ActionEvent event) throws IOException {
		Stage stage = (Stage) ExitButton.getScene().getWindow();
		stage.close();
	}

}