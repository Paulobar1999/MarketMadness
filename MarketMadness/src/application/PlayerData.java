package application;

import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PlayerData {

	static HashMap<String, Integer> heldMap = new HashMap<String, Integer>();
	static int cash = 0;

	public static void initilizePlayerData(int initalCash) {
		cash = initalCash;
		setStockOwned("Red", 0);
		setStockOwned("Green", 0);
		setStockOwned("Yellow", 0);

	}

	public static int tabulateHeldStockValue(String stock) {
		return heldMap.get(stock) * GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
	}

	public static int tabulateAbsoluteTotal() {
		int total = cash;
		for (String stock : heldMap.keySet()) {
			total += heldMap.get(stock) * GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
		}
		return total;
	}

	public static int tabulateTotalStockValue() {
		int total = 0;
		for (String stock : heldMap.keySet()) {
			total += heldMap.get(stock) * GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
		}
		return total;
	}

	// sets the number of a specific stock a player owns
	public static void setStockOwned(String stock, int amount) {

		if (heldMap.containsKey(stock))
			heldMap.put(stock, amount);
		else
			heldMap.putIfAbsent(stock, amount);

	}

	// Increments the number of a specific stock a player owns
	public static void incrementStock(String stock) {

		if (heldMap.containsKey(stock))
			heldMap.put(stock, heldMap.get(stock) + 1);

	}

	// Decrements the number of a specific stock a player owns
	public static void decrementStock(String stock) {

		if (heldMap.containsKey(stock))
			heldMap.put(stock, heldMap.get(stock) - 1);

	}

	// gets the number of a specific stock a player owns
	public static int getStockOwned(String stock) {
		if (heldMap.containsKey(stock))
			return heldMap.get(stock);
		else
			return -1;
	}

	public static void BuyStock(HashMap<String, Text> textMap, HashMap<String, Button> buttonMap, String stock) {

		if (GraphMaster.curPrice(stock, GraphMaster.curDay - 1) <= PlayerData.cash) {
			// deduct price
			PlayerData.cash = PlayerData.cash - GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
			// increment users held stock
			PlayerData.incrementStock(stock);
			UpdateText(textMap, stock);
		}
	}

	public static void SellStock(HashMap<String, Text> textMap, HashMap<String, Button> buttonMap, String stock) {

		if (PlayerData.heldMap.get(stock) > 0) {
			// add price to cash
			PlayerData.cash = PlayerData.cash + GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
			// increment users held stock
			PlayerData.decrementStock(stock);
			UpdateText(textMap, stock);
		}
	}

	// Updates Stock Text
	public static void UpdateText(HashMap<String, Text> textMap, String stock) {
		// update absolute total text, update stock total text, update total cash

		textMap.get("AbsTotal").setText("$" + PlayerData.tabulateAbsoluteTotal());
		textMap.get("StockTotal").setText("$" + PlayerData.tabulateTotalStockValue());
		textMap.get("CashTotal").setText("$" + PlayerData.cash);

		// update aplicable stock text
		if (stock.equals("Red")) {
			textMap.get("RedSO").setText(PlayerData.getStockOwned(stock) + "");
			textMap.get("RedOV").setText("$" + PlayerData.tabulateHeldStockValue(stock));
		}
		if (stock.equals("Green")) {
			textMap.get("GreenSO").setText(PlayerData.getStockOwned(stock) + "");
			textMap.get("GreenOV").setText("$" + PlayerData.tabulateHeldStockValue(stock));
		}
		if (stock.equals("Yellow")) {
			textMap.get("YellowSO").setText(PlayerData.getStockOwned(stock) + "");
			textMap.get("YellowOV").setText("$" + PlayerData.tabulateHeldStockValue(stock));
		}

	}

}
