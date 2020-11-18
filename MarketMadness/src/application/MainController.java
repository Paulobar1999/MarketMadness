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

public class MainController {

	@FXML
	private Button playButton;
	@FXML
	private Button creditsButton;
	@FXML
	private Button ExitButton;


	@FXML
	void GameStart(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}


	@FXML
	void GoToCredits(ActionEvent event) throws IOException {
		// get 'Credits' button to work.
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Credits.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}

	@FXML
	void BackToMainMenu(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));

		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}

	@FXML
	void Exit(ActionEvent event) throws IOException {
		Stage stage = (Stage) ExitButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handle(ActionEvent event) {

	}

}