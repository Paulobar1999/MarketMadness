package application;

import java.util.HashMap;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GraphMaster {

	static HashMap<String, HashMap<Integer, Integer>> MapMap = new HashMap<String, HashMap<Integer, Integer>>();
	static int curDay;
	static int endDay;
	
	
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

	public static int getCurDay() {
		return curDay;
	}

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

	public static int getEndDay() {
		return endDay - curDay;
	}

	//// RETURN PART OF MAP AS SERIES
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

	//// MAP GEN
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

	//// RETURN PRICE OF STOCK
	public static int curPrice(String stock, int day) {
		if (MapMap.containsKey(stock))
			return MapMap.get(stock).get(day);
		return -1;
	}

}
