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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * @author Paul O'Bar, Julian Negrete, Gideon Reyes, Tinesha Johnson, Stephen Otten
 * @version 1.0.0 Build 1 November 20, 2020.
 *
 *  <h1>Program GameController Description</h1><br>
 *
 *  Usage :% java GameController <br><br>
 *
 *	This program manipulates the Game screen of the Market Madness application. It serves as initialization to the game
 *	as well as create a Bankruptcy button, progresses the stock graph, calls the buy and sell stock methods from PlayerData,
 *	and sends the user to the result screen once the game has ended.<br><br>
 *
 *	<h1>ALGORITHM:</h1>
 * 	The textMap holds all text elements of the Game.fxml file <br>
 *	The buttonMap holds all button elements of the Game.fxml file<br><br>
 *
 *	initialize() <br>
 *	Fills the textMap and buttonMap with String references to their respective Text and Button variables<br>
 *	It does this through declaring a Name array of Strings and an array of Text/button elements<br>
 *	After both these maps are filled, the GraphMaster class is called to start the game<br>
 *	Once the stocks are loaded with GraphMaster.GameStart(), we will have to call paintGraph to display
 *	the starting values of the graphs <br>
 *	Style<br>
 *	Add all text to Text Map<br>
 *	Add all buttons to Button Map<br><br>
 *
 *	Bankruptcy() <br>
 *	Essentially a quit button <br>
 *	In the event a game becomes unwinnable, the user can decide to return to the main menu via this method<br><br>
 *
 *	paintGraph()<br>
 *	Used to progress the graph and display the next days information<br><br>
 *
 *	EndScreen() <br>
 *	Called once the game has ended and the user has no choice other than to continue to the results screen<br>
 *	The method loads the ResultScreen.fxml, as well as passes the players 'score' to initializeResults<br>
 *	Put 'css' file for results screen here, if at all needed<br><br>
 *
 *	BuyStock() <br>
 *	Parses a portion of the button that calls its name to extract the name of the stock (i.e. "RedBuy" will be parsed to "Red", and passed
 *	to the PlayerData.BuyStock method)<br><br>
 *
 *	SellStock() <br>
 *	Parses a portion of the button that calls its name to extract the name of the stock (i.e. "RedSell" will be parsed to
 *	"Red", and passed to the PlayerData.SellStock method)<br>
 */
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
	@FXML
	private VBox redBox;
	@FXML
	private VBox greenBox;
	@FXML
	private VBox yellowBox;

	/**
	 * The textMap holds all text elements of the Game.fxml file The buttonMap holds
	 * all button elements of the Game.fxml file
	 */
	static HashMap<String, Text> textMap = new HashMap<String, Text>();
	static HashMap<String, Button> buttonMap = new HashMap<String, Button>();

	/**
	 * The initialize method fills the textMap and buttonMap with String references to
	 * their respected Text and Button variables. It does this through declaring a
	 * Name array of Strings and an array of Text/button elements. After both these
	 * maps are filled, the GraphMaster class is called to start the game. Once the
	 * stocks are loaded with GraphMaster.GameStart() we will have to call
	 * paintGraph to display the starting values of the graphs.
	 *
	 * @see GraphMaster.GameStart();
	 * @see paintGraph();
	 */
	@FXML
	void initialize() {
		// Style
		// LineChart.setCreateSymbols(false);
		LineChart.getXAxis().setOpacity(.2);
		LineChart.getYAxis().setOpacity(.5);

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

	/**
	 * The Bankruptcy method is essentially a quit button. In the event a game becomes
	 * unwinnable the user can decide to return to the main menu via this method.
	 *
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void Bankruptcy(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}

	/**
	 * The paintGraph method is used to progress the graph and display the next days
	 * information.
	 */
	@FXML
	void paintGraph() {
		GraphMaster.progressDay(LineChart, textMap, buttonMap);
	}

	/**
	 * The EndScreen method is called once the game has ended and the user has no
	 * choice other than to continue to the results screen. The method Loads the
	 * ResultScreen.fxml, As well as passes the players 'score' to initializeResults
	 *
	 * @see getUserController.initializeResults();
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void EndScreen(ActionEvent event) throws IOException {
		FXMLLoader resultsLoader = new FXMLLoader(getClass().getResource("ResultScreen.fxml"));
		Parent root = resultsLoader.load();
		ResultController getUserController = resultsLoader.getController();
		getUserController.initializeResults(absTotalText);
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// put 'css' file for results screen here, if at all needed.
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
	}

	/**
	 * The BuyStock method parses a portion of the button that call to its' name to
	 * extract the name of the stock ie "RedBuy" will be parsed to "Red", and passed
	 * to the PlayerData.BuyStock method.
	 *
	 * @see PlayerData.BuyStock();
	 * @param event must be a button named {STOCKNAME}Buy
	 */
	@FXML
	void BuyStock(ActionEvent event) {
		String stock = ((Button) event.getSource()).getId();
		stock = stock.substring(0, stock.length() - 3);
		PlayerData.BuyStock(textMap, buttonMap, stock);
	}

	/**
	 * The SellStock method parses a portion of the button that call to its' name to
	 * extract the name of the stock ie "RedSell" will be parsed to "Red", and passed
	 * to the PlayerData.SellStock method.
	 *
	 * @see PlayerData.SellStock();
	 * @param event must be a button named {STOCKNAME}Sell
	 */
	@FXML
	void SellStock(ActionEvent event) {
		String stock = ((Button) event.getSource()).getId();
		stock = stock.substring(0, stock.length() - 4);
		PlayerData.SellStock(textMap, buttonMap, stock);
	}

}