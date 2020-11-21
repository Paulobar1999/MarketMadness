package application;

import java.util.HashMap;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GraphMaster {

	/**
	 * MapMap is a Hashmap containing a String, the stock name, and a secondary
	 * HashMap of Integers(Day value) and Integers(Stock Value)
	 */
	static HashMap<String, HashMap<Integer, Integer>> MapMap = new HashMap<String, HashMap<Integer, Integer>>();
	/**
	 * CurDay and endDay are used to increment and display the current day value
	 */
	static int curDay;
	static int endDay;

	/**
	 * The GameStart method initializes the curDay and endDay value, as well as
	 * filling all three stock with ALL their values by calling masterGen. The
	 * PlayerData is also called to initlize the players starting cash.
	 * 
	 * @param curDay the Integer value of the current day
	 * @param endDay the Integer value of the days left
	 * @param MapMap a Map that contains a Stock name, and a secondary HashMap that
	 *               has the day value and the stock value, both Integers.
	 * @see masterGen();
	 * @see PlayerData.initilizePlayerData();
	 */
	public static void GameStart() {
		// Day values
		curDay = 1;
		endDay = 50 + 1;
		// init Player data with starting cash
		PlayerData.initilizePlayerData(500);
		// Add and Generate maps
		MapMap.put("Red", masterGen(30, 100, 1));
		MapMap.put("Yellow", masterGen(15, 100, 2));
		MapMap.put("Green", masterGen(5, 100, 3));
	}

	/**
	 * getCurDay returns the current day value
	 * 
	 * @return curDay Current day value
	 */
	public static int getCurDay() {
		return curDay;
	}

	/**
	 * ProgressDay Cleans off the LineChart and proceeds to redraw all graphs one
	 * day in advance, giving the appearance that new data has been generated when
	 * in reality we are just showing more of each stocks already plotted graphs.
	 * Along with this the method updates all UI elements with their current prices.
	 * Eventually this method will trigger the 'end of game' where all buttons are
	 * disabled other than 'RESULTS'.
	 * 
	 * @param LineChart The Linechart to display the graph on
	 * @param uiMap     TextMap including all Text elements from GameController.java
	 * @param buttonMap ButtonMap including all Button elements from
	 *                  GameController.java
	 */
	@SuppressWarnings("unchecked")
	public static void progressDay(LineChart<?, ?> LineChart, HashMap<String, Text> uiMap,
			HashMap<String, Button> buttonMap) {
		int currentDay = GraphMaster.getCurDay();
		String[] stockArray = { "Red", "Yellow", "Green" };

		// IncrementDay value
		curDay++;

		// Style
		LineChart.setAnimated(false);
		LineChart.setLegendVisible(false);

		// Clean Last Graph
		LineChart.getData().clear();

		// Add series
		for (String stock : stockArray) {
			LineChart.getData().addAll(GraphMaster.Part(stock, currentDay));
		}

		// Tabulate absolute total text
		uiMap.get("AbsTotal").setText("$" + PlayerData.tabulateAbsoluteTotal() + "");
		// Tabulate stock total text
		uiMap.get("StockTotal").setText("$" + PlayerData.tabulateTotalStockValue() + "");
		// Tabulate total cash
		uiMap.get("CashTotal").setText("$" + PlayerData.cash);

		// Tabulate and post amount of held stock
		uiMap.get("RedSO").setText(PlayerData.getStockOwned("Red") + "");
		uiMap.get("GreenSO").setText(PlayerData.getStockOwned("Green") + "");
		uiMap.get("YellowSO").setText(PlayerData.getStockOwned("Yellow") + "");

		// Tabulate and post the value of your held stock
		uiMap.get("RedOV").setText("$" + PlayerData.tabulateHeldStockValue("Red") + "");
		uiMap.get("GreenOV").setText("$" + PlayerData.tabulateHeldStockValue("Green") + "");
		uiMap.get("YellowOV").setText("$" + PlayerData.tabulateHeldStockValue("Yellow") + "");

		// Post All Market Values

		uiMap.get("RedMV").setText("$" + GraphMaster.curPrice("Red", currentDay));
		uiMap.get("GreenMV").setText("$" + GraphMaster.curPrice("Green", currentDay));
		uiMap.get("YellowMV").setText("$" + GraphMaster.curPrice("Yellow", currentDay));

		// Change Days left
		uiMap.get("daycount").setText(endDay - curDay + "");

		// Disable next button, End of game
		if (endDay - curDay == 0) {
			buttonMap.get("Next").setDisable(true);
			for (String butName : buttonMap.keySet()) {
				buttonMap.get(butName).setDisable(true);
			}
			buttonMap.get("End").setDisable(false);
			buttonMap.get("End").setVisible(true);
		}
	}

	/**
	 * Calculates How many days are left
	 * 
	 * @return Days left (endDay - curDay)
	 */
	public static int getEndDay() {
		return endDay - curDay;
	}

	/**
	 * Part returns up to a day of stock values as a XYChart Series.
	 * 
	 * @param stock Name of stock
	 * @param day   Current day value
	 * @return XYChart.Series X = Day value Y = Stock Value at Day
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static XYChart.Series Part(String stock, int day) {
		HashMap<Integer, Integer> masterMap = null;
		if (MapMap.containsKey(stock))
			masterMap = MapMap.get(stock);
		XYChart.Series seriesSet = new XYChart.Series();
		for (int i = 0; i < day; i++) {
			seriesSet.getData().add(new Data("Day " + Integer.toString(i), masterMap.get(i)));
		}
		return seriesSet;
	}

	/**
	 * masterGen is the formulation method for all values in the stock. It takes in
	 * an int variance, an int, inital value and a cashMultiplier. The method
	 * returns a HashMap of Intergers (Day) and Intergers (stock value). The Size of
	 * the Map is set via the endDay integer. Within the while loop there is a
	 * safeguard eliminates all zero values, as they would break the gameplay. Each
	 * walk through the while loop randomly generates a calculatedVariance which has
	 * a chance to be negative or positive causing the stock to dive or rise.
	 * 
	 * @param variance       an integer that determines the likely hood of the value
	 *                       of a stock to change day by day
	 * @param inital         an integer that sets the starting value of the stock
	 * @param cashMultiplier an integer that multiplies the stocks value, ie making
	 *                       them more sporadic
	 * @return HashMap of Integers(Day) and Integers (Stock Value)
	 */
	public static HashMap<Integer, Integer> masterGen(int variance, int inital, int cashMultiplier) {
		HashMap<Integer, Integer> masterMap = new HashMap<Integer, Integer>();
		int Size = endDay;
		masterMap.put(0, inital);
		for (int i = 1; i < Size + 1; i++) {
			int heldValue = -1;
			while (heldValue < 1) {
				// Stock Algorithm
				int calculatedVariance = (int) (Math.random() * (2.2 * variance) - variance);
				heldValue = masterMap.get(i - 1) + (int) (calculatedVariance * cashMultiplier);
			}
			masterMap.put(i, heldValue);
		}
		return masterMap;
	}

	/**
	 * curPrice returns the stock value at a given day.
	 * 
	 * @param stock valid stock name 
	 * @param day valid day value
	 * @return current price of stock
	 */
	public static int curPrice(String stock, int day) {
		if (MapMap.containsKey(stock))
			return MapMap.get(stock).get(day);
		return -1;
	}

}
