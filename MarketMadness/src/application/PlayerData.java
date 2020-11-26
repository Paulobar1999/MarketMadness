package application;
import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
/**
 * @author Paul O'Bar, Julian Negrete, Gideon Reyes, Tinesha Johnson, Stephen Otten
 * @version 1.0.0 Build 1 November 20, 2020.
 *
 *  <h1>Program PlayerData Description</h1><br>
 *
 *  Usage :% java PlayerData <br><br>
 *
 *	This program manipulates the player data of the Market Madness application. It manages the stocks the player owns.
 *	It provides the logic for incrementing stock, decrementing stock, buying and selling stock, and updating the stock
 *	that the player owns. <br><br>
 *
 *	<h1>ALGORITHM:</h1>
 *	Create HashMap heldMap to hold a stock name and the quantity of the stock the play owns <br>
 *	Create cash variable to hold player cash<br><br>
 *
 *	initilizePlayerData() <br>
 *	Called at the start of a game<br>
 *	It sets the players cash to initalCash and sets all the players holding in all stocks to zero<br><br>
 *
 *	tabulateHeldStockValue() <br>
 *	Takes a stock name and multiplies the players held quantity of the stock by the stocks current price<br><br>
 *
 *	tabulateAbsoluteTotal() <br>
 *	Returns the players total cash added with the value of all currently held stocks, resulting in an absolute total of assets<br><br>
 *
 *	tabulateTotalStockValue() <br>
 *	Returns the players total value of all currently held stocks<br><br>
 *
 *	setStockOwned() <br>
 *	Sets the quantity of stock a player owns. It intakes a stock name and an amount to set the held value to<br><br>
 *
 *	incrementStock() <br>
 *	Increments the held quantity of a given stock by one<br><br>
 *
 *	decrementStock() <br>
 *	Decrements the held quantity of a given stock by one<br><br>
 *
 *	getStockOwned() <br>
 *	Returns the quantity (int) of held stock a player owns. Will return -1 if the provided stock is not found within heldMap<br><br>
 *
 *	BuyStock() <br>
 *	Checks if a player has the necessary funds to purchase a given stock <br>
 *	If they do, the stock held quantity will be incremented and the price of the stock deducted from the players cash reserves<br><br>
 *
 *	SellStock() <br>
 *	Checks if the player currently holds the stock in question<br>
 *	If they do hold at least 1 they are able to sell it, deducting the held value of the stock and adding its value to the
 *	players cash reserves<br><br>
 *
 *	UpdateText() <br>
 *	Is called once a stock has been bought or sold, it updates display text on the game screen<br>
 *	Update absolute total text, update stock total text, update total cash<br>
 *	Update applicable stock text<br>
 */
public class PlayerData {

	/**
	 * heldMap holds a stock name and the quantity of the stock the play owns
	 */
	static HashMap<String, Integer> heldMap = new HashMap<String, Integer>();

	/**
	 * Player cash
	 */
	static int cash = 0;

	/**
	 * initilizePlayerData Is called at the start of a game, It sets the players
	 * cash to initalCash and sets all the players holding in all stocks to zero.
	 * @see setStockOwned();
	 * @param initalCash
	 */
	public static void initilizePlayerData(int initalCash) {
		cash = initalCash;
		setStockOwned("Red", 0);
		setStockOwned("Green", 0);
		setStockOwned("Yellow", 0);

	}

	/**
	 * tabulateHeldStockValue Takes a stock name and multiplies the players held
	 * quantity of the stock by the stocks current price.
	 *
	 * @param stock Name of Stock
	 * @return Stock value multiplied by quantity of stock held
	 */
	public static int tabulateHeldStockValue(String stock) {
		return heldMap.get(stock) * GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
	}

	/**
	 * tabulateAbsoluteTotal returns the players total cash added with the value of
	 * all currently held stocks, resulting in an absolute total of assets.
	 * @see GraphMaster.curPrice();
	 * @return absolute total of all assets, cash and stock
	 */
	public static int tabulateAbsoluteTotal() {
		int total = cash;
		for (String stock : heldMap.keySet()) {
			total += heldMap.get(stock) * GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
		}
		return total;
	}

	/**
	 * tabulateTotalStockValue returns the players total value of all currently held
	 * stocks.
	 *
	 * @see GraphMaster.curPrice();
	 * @return total of all held stocks
	 */
	public static int tabulateTotalStockValue() {
		int total = 0;
		for (String stock : heldMap.keySet()) {
			total += heldMap.get(stock) * GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
		}
		return total;
	}

	/**
	 * setStockOwned sets the quantity of stock a player owns. It intakes a stock
	 * name and an amount to set the held value to.
	 *
	 * @param stock Name of stock
	 * @param amount Quantity of stock
	 */
	public static void setStockOwned(String stock, int amount) {

		if (heldMap.containsKey(stock))
			heldMap.put(stock, amount);
		else
			heldMap.putIfAbsent(stock, amount);

	}

	/**
	 * incrementStock increments the held quantity of a given stock by one.
	 * @param stock Name of stock
	 */
	public static void incrementStock(String stock) {
		if (heldMap.containsKey(stock))
			heldMap.put(stock, heldMap.get(stock) + 1);
	}

	/**
	 * decrementStock decrements the held quantity of a given stock by one.
	 *
	 * @param stock Name of stock
	 */
	public static void decrementStock(String stock) {

		if (heldMap.containsKey(stock))
			heldMap.put(stock, heldMap.get(stock) - 1);

	}

	/**
	 * getStockOwned returns the quantity (int) of held stock a player owns. Will
	 * return -1 if the provided stock is not found within heldMap.
	 *
	 * @param stock Name of stock
	 * @return Quantity of stock
	 */
	public static int getStockOwned(String stock) {
		if (heldMap.containsKey(stock))
			return heldMap.get(stock);
		else
			return -1;
	}

	/**
	 * BuyStock checks if a player has the necessary funds to purchase a given
	 * stock, If they do the stock held quantity will be incremented and the price
	 * of the stock deducted from the players cash reserves.
	 *
	 * @param textMap Map of all text elements in GameController
	 * @param buttonMap Map of all button elements in GameController
	 * @param stock Name of stock
	 */
	public static void BuyStock(HashMap<String, Text> textMap, HashMap<String, Button> buttonMap, String stock) {

		if (GraphMaster.curPrice(stock, GraphMaster.curDay - 1) <= PlayerData.cash) {
			// deduct price
			PlayerData.cash = PlayerData.cash - GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
			// increment users held stock
			PlayerData.incrementStock(stock);
			UpdateText(textMap, stock);
		}
	}

	/**
	 * SellStock first checks if the player currently holds the stock in question,
	 * if they do hold at least 1 they are able to sell it, deducting the held value
	 * of the stock and adding its value to the players cash reserves.
	 *
	 * @param textMap  Map of all text elements in GameController
	 * @param buttonMap Map of all button elements in GameController
	 * @param stock Name of Stock
	 */
	public static void SellStock(HashMap<String, Text> textMap, HashMap<String, Button> buttonMap, String stock) {
		if (PlayerData.heldMap.get(stock) > 0) {
			PlayerData.cash = PlayerData.cash + GraphMaster.curPrice(stock, GraphMaster.curDay - 1);
			PlayerData.decrementStock(stock);
			UpdateText(textMap, stock);
		}
	}

	/**
	 * UpdateText is called once a stock has been bought or sold, it updates display
	 * text on the game screen.
	 *
	 * @param textMap Map of all text elements in GameController
	 * @param stock Name of Stock
	 */
	public static void UpdateText(HashMap<String, Text> textMap, String stock) {
		// update absolute total text, update stock total text, update total cash
		textMap.get("AbsTotal").setText("$" + PlayerData.tabulateAbsoluteTotal());
		textMap.get("StockTotal").setText("$" + PlayerData.tabulateTotalStockValue());
		textMap.get("CashTotal").setText("$" + PlayerData.cash);

		// update applicable stock text
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
