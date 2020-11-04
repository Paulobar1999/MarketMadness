package application;

import java.util.HashMap;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class GraphMaster {
	
	static HashMap<String, HashMap<Integer, Integer>> MapMap = new HashMap<String, HashMap<Integer, Integer>>();

	static int curDay;
	static int endDay;

	public static void GameStart() {
		curDay = 1;
		endDay = 51;

		MapMap.put("Red", masterGen(5, 100, 3));
		MapMap.put("Yellow", masterGen(15, 100, 2));
		MapMap.put("Green", masterGen(30, 100, 1));

	}

	public static int getCurDay() {
		return curDay;
	}

	public static void progressDay() {
		curDay++;
	}

	public static int getEndDay() {
		return endDay;
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
