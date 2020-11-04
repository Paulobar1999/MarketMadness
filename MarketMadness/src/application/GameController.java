package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class GameController {

	@FXML
	private Button bankruptcyButton;

	@FXML
	private Button NextButton;
	
    @FXML
    private Button EndButton;

	@FXML
	private LineChart<?, ?> LineChart;

	@FXML
	private Text YellowMV;

	@FXML
	private Text RedMV;

	@FXML
	private Text GreenMV;

	@FXML
	private Text dayCount;

	int daysLeft;

	@FXML
	void initialize() {
		GraphMaster.GameStart();
		daysLeft = GraphMaster.endDay;
		paintGraph();
	}

	@FXML
	void Bankruptcy(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}

	@SuppressWarnings("unchecked")
	@FXML
	void paintGraph() {
		// Vars
		int currentDay = GraphMaster.getCurDay();
		String[] stockArray = { "Red", "Yellow", "Green" };

		// Style
		LineChart.setAnimated(false);
		LineChart.setLegendVisible(false);

		// Clean Last Graph
		LineChart.getData().clear();

		// Add series
		for (String stock : stockArray) {
			LineChart.getData().addAll(GraphMaster.Part(stock, currentDay));
		}

		// Post All 3 prices
		RedMV.setText("$" + GraphMaster.curPrice("Red", currentDay));
		GreenMV.setText("$" + GraphMaster.curPrice("Green", currentDay));
		YellowMV.setText("$" + GraphMaster.curPrice("Yellow", currentDay));

		// Change Days left
		dayCount.setText(daysLeft - 1 + "");

		// IncrementDay value
		GraphMaster.progressDay();
		daysLeft--;

		// Disable next button
		if (daysLeft == 0) {
			NextButton.setDisable(true);
			EndButton.setVisible(true);
		}
	}
	
	@FXML
	void EndScreen(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("ResultScreen.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}

	@FXML
	void handle(ActionEvent event) {

	}

}