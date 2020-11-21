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

	@FXML
	Text PlayerHighScore;
	@FXML
	Text PreviousHighScore;
	@FXML
	Text DescriptionBox;
	@FXML
	Button ExitButton;

	// Function gets needed highscore values in int form.
	/**
	 * initializeResults takes in a Text element and cleans it to extract an integer
	 * value, this value is sent to compareResults along with an Integer extracted
	 * from a file called score.txt.
	 * @see compareResults();
	 * @param absTotalText Text containing the absolute total asset value of the player
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