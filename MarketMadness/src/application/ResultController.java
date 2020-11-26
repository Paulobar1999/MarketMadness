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

/**
 * @author Paul O'Bar, Julian Negrete, Gideon Reyes, Tinesha Johnson, Stephen Otten
 * @version 1.0.0 Build 1 November 20, 2020.
 *
 *  <h1>Program ResultController Description</h1><br>
 *
 *  Usage :% java ResultController <br><br>
 *
 *	This program manipulates the results screen of the Market Madness application. It takes the user's
 *	score and displays it on the screen. It also compares it to the running high score of the game. If
 *	the user's score is greater than the high score, it will replace it as the new high score. If the user
 *	wants to play again they can click the "Main Menu" button. If they would like to exit the game they can
 *	click the "Exit" button.<br><br>
 *
 *	<h1>ALGORITHM:</h1>
 *	initializeResults() <br>
 *	Take in a Text element and clean it to extract an integer value, this value is sent to compareResults along with an
 *	Integer extracted from a file called score.txt<br>
 *	Get players high score from current instance<br>
 *	Get high score from file<br>
 *	Call compareResults to compare results to high score<br><br>
 *
 *	compareResults() <br>
 *	Determines if the player has beat the recored high score, if so the according text will be displayed and the new high score
 *	will be written to file using updateHighScore()<br>
 *	If high score is greater than the previous, update high score and display the appropriate message<br>
 *	If, by some divine intervention, a player matches their previous high score, display the appropriate message<br>
 *	If user fails to get a higher score, display the appropriate message <br><br>
 *
 *	updateHighScore() <br>
 *	Called only if the player has achieved a score that exceeds the score residing in score.txt, In this case score.txt will
 *	be truncated and the new high score will be written <br><br>
 *
 *	Restart() <br>
 *	Loads the player back to the main menu in Main.fxml<br><br>
 *
 *	Exit() <br>
 *	Exits the application<br>
 */
public class ResultController {

	@FXML
	Text PlayerHighScore;
	@FXML
	Text PreviousHighScore;
	@FXML
	Text DescriptionBox;
	@FXML
	Button ExitButton;

	/**
	 * initializeResults takes in a Text element and cleans it to extract an integer
	 * value, this value is sent to compareResults along with an Integer extracted
	 * from a file called score.txt
	 * @see compareResults()
	 * @param Text absTotalText
	 * @throws FileNotFoundException
	 */
	void initializeResults(Text absTotalText) throws FileNotFoundException {
		// get players high score from current instance
		int currentPlayerScore = Integer.parseInt(absTotalText.getText().replace("$", ""));
		// get highscore from file
		File gradeFile = new File("score.txt");
		Scanner getHighscore = new Scanner(gradeFile);
		int storedHighScore = Integer.parseInt(getHighscore.nextLine().replace("$", ""));
		getHighscore.close();
		compareResults(currentPlayerScore, storedHighScore);
	}

	/**
	 * compareResults determines if the player has beat the recored highscore, if
	 * so the according text will be displayed and the new highscore will be written
	 * to file using updateHighScore.
	 *
	 * @see updateHighScore()
	 * @param currentPlayerScore The players final score
	 * @param storedHighScore The current highest score, residing in score.txt
	 * @throws FileNotFoundException
	 */
	void compareResults(int currentPlayerScore, int storedHighScore) throws FileNotFoundException {

		PlayerHighScore.setText(Integer.toString(currentPlayerScore));
		PreviousHighScore.setText(Integer.toString(storedHighScore));

		// if highscore is greater than the previous
		if (currentPlayerScore > storedHighScore) {
			updateHighScore(currentPlayerScore);
			DescriptionBox.setText("You were $" + (currentPlayerScore - storedHighScore) + " over the highscore!");
		}
		// If, by some divine intervention, a player matches their previous highscore
		else if (currentPlayerScore == storedHighScore) {
			DescriptionBox.setText("You matched the High Score!");
		}
		// if user fails to get a higher score
		else {
			DescriptionBox.setText("You were $" + (storedHighScore - currentPlayerScore) + " under the highscore!");
		}
	}

	/**
	 * updateHighScore is called only if the player has achieved a score that
	 * exceeds the score residing in score.txt, In this case score.txt will be
	 * truncated and the new highscore will be written.
	 *
	 * @param currentPlayerScore  The players final score
	 * @throws FileNotFoundException
	 */
	void updateHighScore(int currentPlayerScore) throws FileNotFoundException {
		PrintWriter fileWriter = new PrintWriter("score.txt");
		fileWriter.print("");
		fileWriter.print(currentPlayerScore);
		fileWriter.close();
	}

	/**
	 * Restart loads the player back to the main menu in Main.fxml
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void Restart(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}

	/**
	 * Exit, exits the application.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void Exit(ActionEvent event) throws IOException {
		Stage stage = (Stage) ExitButton.getScene().getWindow();
		stage.close();
	}
}