package application;

import java.util.HashMap;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class GraphMaster {

	//	MAPS
	static HashMap<Integer,Integer> RedMap    = masterGen(10,100,2);
	static HashMap<Integer,Integer> OrangeMap = masterGen(10,100,2);
	static HashMap<Integer,Integer> GreenMap  = masterGen(10,100,2);
	
	public static void GameStart() {
		
		RedMap    = masterGen(10,100,2);
		OrangeMap = masterGen(10,100,2);
		GreenMap  = masterGen(10,100,2);
		
	}
	
	
	////									RETURN PART OF MAP AS SERIES
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static XYChart.Series Part(int stock,int day)  {
		
		HashMap<Integer,Integer> masterMap = null;
		
		//Make Map of Maps?
		if(stock==1)
			masterMap = RedMap;
		else if(stock==2)
			masterMap = OrangeMap;		
		else if(stock==3)
			masterMap = GreenMap;

		XYChart.Series seriesSet = new XYChart.Series();
		for (int i = 0; i < day; i++) {
			seriesSet.getData().add(new Data("Day "+Integer.toString(i), masterMap.get(i) ));
		}
		return seriesSet;
	} 
	
	////									MAP GEN
	public static HashMap<Integer,Integer>  masterGen( int variance, int inital, int cashMultiplier) {
		HashMap<Integer,Integer> masterMap = new HashMap<Integer, Integer>();
		int Size = 100;
		masterMap.put(0,inital);
		for (int i = 1; i < Size+1; i++) {
			int heldValue = -1;
			while(heldValue<1) {
			//Stock Algorithm
			int calculatedVariance = (int) (Math.random() * ( 2.2 * variance ) -  variance);
			heldValue = masterMap.get(i-1) +  (int)(calculatedVariance*cashMultiplier) ;
			}
			masterMap.put(i,heldValue);
		}
		return masterMap;
	}

}
