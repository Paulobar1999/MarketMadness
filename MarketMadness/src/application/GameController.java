package application;

import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	@FXML
	private Text yellowSOText;
	@FXML
	private Text redSOText;
	@FXML
	private Text greenSOText;
	@FXML
	private Text yellowOVText;
	@FXML
	private Text redOVText;
	@FXML
	private Text greenOVText;
	@FXML
	private Button RedBuy;
	@FXML
	private Button GreenBuy;
	@FXML
	private Button YellowBuy;
	@FXML
	private Button RedSell;
	@FXML
	private Button GreenSell;
	@FXML
	private Button YellowSell;
	@FXML
	private Text absTotalText;
	@FXML
	private Text totalStockText;
	@FXML
	private Text totalCashText;

	static HashMap<String, Text> textMap = new HashMap<String, Text>();
	static HashMap<String, Button> buttonMap = new HashMap<String, Button>();

	@FXML
	void initialize() {

		// Add all text to Text Map
		String[] textNames = { "RedMV", "GreenMV", "YellowMV", "daycount", "RedSO", "GreenSO", "YellowSO", "RedOV",
				"GreenOV", "YellowOV", "AbsTotal", "StockTotal", "CashTotal" };
		Text[] textRef = { RedMV, GreenMV, YellowMV, dayCount, redSOText, greenSOText, yellowSOText, redOVText,
				greenOVText, yellowOVText, absTotalText, totalStockText, totalCashText };
		for (int i = 0; i < textNames.length; i++) {
			textMap.put(textNames[i], textRef[i]);
		}
		// Add all buttons to Button Map
		String[] buttonNames = { "Bankruptcy", "Next", "End", "RedBuy", "GreenBuy", "YellowBuy", "RedSell", "GreenSell",
				"YellowSell" };
		Button[] buttonRef = { bankruptcyButton, NextButton, EndButton, RedBuy, GreenBuy, YellowBuy, RedSell, GreenSell,
				YellowSell };
		for (int i = 0; i < buttonNames.length; i++) {
			buttonMap.put(buttonNames[i], buttonRef[i]);
		}
		GraphMaster.GameStart();
		paintGraph();
	}

	@FXML
	void Bankruptcy(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}

	@FXML
	void paintGraph() {
		GraphMaster.progressDay(LineChart, textMap, buttonMap);
	}

	@FXML
	void EndScreen(ActionEvent event) throws IOException {
		FXMLLoader resultsLoader = new FXMLLoader(getClass().getResource("ResultScreen.fxml"));
		Parent root = resultsLoader.load();

        ResultController getUserController = resultsLoader.getController();
        getUserController.initializeResults( absTotalText );
		
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		//put 'css' file for results screen here, if at all needed.
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		window.setScene(scene);
	}

	@FXML
	void BuyStock(ActionEvent event) {
		String stock = ((Button) event.getSource()).getId();
		stock = stock.substring(0, stock.length() - 3);
		PlayerData.BuyStock(textMap, buttonMap, stock);
	}

	@FXML
	void SellStock(ActionEvent event) {
		String stock = ((Button) event.getSource()).getId();
		stock = stock.substring(0, stock.length() - 4);
		PlayerData.SellStock(textMap, buttonMap, stock);
	}

}