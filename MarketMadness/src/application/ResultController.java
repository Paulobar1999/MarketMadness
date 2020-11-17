package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ResultController {
	
	@FXML  Text PlayerHighScore;
	@FXML  Text PreviousHighScore;
	@FXML  Text DescriptionBox;
	
	@FXML Button ExitButton;
	
	//Function gets needed highscore values in int form.
	void initializeResults(Text absTotalText) throws FileNotFoundException {
		//get players high score from current instance
		int currentPlayerScore = Integer.parseInt( absTotalText.getText().replace("$", "" ) );
		
		//get highscore from file
		File gradeFile = new File("score.txt");
		Scanner getHighscore = new Scanner(gradeFile);
		int  storedHighScore = Integer.parseInt( getHighscore.nextLine().replace("$", "" ) );
		getHighscore.close();
		compareResults(currentPlayerScore, storedHighScore);
	}
	
	//Function compares the values and determines what outcome to make.
	void compareResults( int currentPlayerScore, int storedHighScore ) throws FileNotFoundException {

		PlayerHighScore.setText( Integer.toString( currentPlayerScore ) );
		PreviousHighScore.setText( Integer.toString( storedHighScore ) );
		
		//if highscore is greater than the previous
		if( currentPlayerScore > storedHighScore ) {
			updateHighScore(currentPlayerScore);
			DescriptionBox.setText("You were $" + (currentPlayerScore - storedHighScore) + " over the previous highscore!");
		}
		//If, by some divine intervention, a player matches their previous highscore
		else if(currentPlayerScore == storedHighScore) {
			DescriptionBox.setText("You matched the previous High Score!");
		}
		//if user fails to get a higher score
		else {
			DescriptionBox.setText("You were $" + (storedHighScore - currentPlayerScore) + " under the previous highscore!");
		}
	}
	
	//Function updates high score value only if there is a new highscore.
	void updateHighScore( int currentPlayerScore ) throws FileNotFoundException {
		PrintWriter fileWriter = new PrintWriter("src/application/highscore.txt");
		fileWriter.print("");
		fileWriter.print( currentPlayerScore );
		fileWriter.close();
	}
	
	//Back to main menu
	@FXML
	void Restart(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}
	
	//Exit the application.
	@FXML
	void Exit(ActionEvent event) throws IOException {
		Stage stage = (Stage) ExitButton.getScene().getWindow();
		stage.close();
	}
}