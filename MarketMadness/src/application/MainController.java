package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;




public class MainController {

    @FXML
    private Button playButton;

    @FXML
	void GameStart(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}
    
    @FXML
    void handle(ActionEvent event) {

    }

}