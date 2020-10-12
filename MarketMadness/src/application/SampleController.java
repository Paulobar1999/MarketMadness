package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;









public class SampleController {
	@FXML
	private Button play_btn;//button for user to play the game

	@FXML
	private Button quit_btn;//first scene option to quit the game
	
	@FXML
	private Button d_bank;// bankruptcy button

	@FXML
	private Button G_start;// game start button on the game scene

	@FXML
	private LineChart<?, ?> lineChart; // line chart for the stocks

	@FXML
	private CategoryAxis x;// the chart x axis 

	@FXML
	private NumberAxis y;// the chart y axis 


	@FXML
	private Button G_sell;// Green Energy sell button

	@FXML
	private Button G_buy;// Green Energy buy button

	@FXML
	private Button R_sell;// Red sell button

	@FXML
	private Button R_buy;// Red buy button

	@FXML
	private Button O_sell;// Orange sell button

	@FXML
	private Button O_buy;// Orange buy button
	 
	@FXML
    private TextField totalAssets;//totalAsset

    @FXML
    private TextField numVar;// number variance

    @FXML
    private TextField numChart; // number of chart

    @FXML
    private TextField tItems;//total items

    @FXML
    private TextField Gshares;// number green shares 

    @FXML
    private TextField gStock;// green shares amount

    @FXML
    private TextField rShares;// number red share 

    @FXML
    private TextField rStock;// red share amount

    @FXML
    private TextField oShare;// number of orange shares

    @FXML
    private TextField oStock;// amount of orange shares
    
    
    @FXML
    private Button f_quitbtn;// the quit button for the fail scene

    @FXML
    private Button agnBtn;// play again button on the fail scene


    @FXML // the action button for the play button
    void handle(ActionEvent event) {

    }

}