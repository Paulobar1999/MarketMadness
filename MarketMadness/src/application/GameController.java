package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;




public class GameController {

    @FXML
    private Button bankruptcyButton;

    @FXML
    private LineChart<?, ?> LineChart;
    
    @FXML
	void Bankruptcy(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		Main.primaryStage.setScene(scene);
	}
    
    int globalDay = 1;
    @SuppressWarnings("unchecked")
	@FXML
    void paintGraph() {
    	//Style
        LineChart.setAnimated(false); 
	    LineChart.setLegendVisible(false);
	    //LineChart.setCreateSymbols(false);

	    //Clean Last Graph
		LineChart.getData().clear();
		
		//Add series
		LineChart.getData().addAll(GraphMaster.Part(globalDay));
		
		//IncrementDay value
		globalDay++;
    	
    }
    @FXML
    void handle(ActionEvent event) {

    }

}