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

		// Style
		LineChart.setAnimated(false);
		LineChart.setLegendVisible(false);

		// Clean Last Graph
		LineChart.getData().clear();

		// Add series
		LineChart.getData().addAll(GraphMaster.Part(1, currentDay));
		LineChart.getData().addAll(GraphMaster.Part(2, currentDay));
		LineChart.getData().addAll(GraphMaster.Part(3, currentDay));

		// Post All 3 prices
		RedMV.setText("$" + GraphMaster.RedMap.get(currentDay));
		GreenMV.setText("$" + GraphMaster.GreenMap.get(currentDay));
		YellowMV.setText("$" + GraphMaster.OrangeMap.get(currentDay));

		// Change Days left
		dayCount.setText(daysLeft - 1 + "");

		// IncrementDay value
		GraphMaster.progressDay();
		daysLeft--;

		// Disable next button
		if (daysLeft == 0) {
			NextButton.setDisable(true);
		}
	}

	@FXML
	void handle(ActionEvent event) {

	}

}