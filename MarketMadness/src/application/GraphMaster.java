package application;

import java.util.HashMap;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class GraphMaster {

	static HashMap<Integer,Integer> RedMap= masterGen(10,100,2);

	public static void main(String[] args) {
		
	}
	
	
	
	////									RETURN PART OF MAP AS SERIES
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static XYChart.Series Part(int day)  {
		HashMap<Integer,Integer> masterMap = null;
		masterMap = RedMap;
		XYChart.Series seriesSet = new XYChart.Series();
		for (int i = 0; i < day; i++) {
			seriesSet.getData().add(new Data("Day "+Integer.toString(i), masterMap.get(i) ));
		}
		return seriesSet;
	} 
	
	////										MAP GEN
	public static HashMap<Integer,Integer>  masterGen( int variance, int inital, int cashMultiplier) {
		HashMap<Integer,Integer> masterMap = new HashMap<Integer, Integer>();
		int Size = 100;
		masterMap.put(0,inital);
		for (int i = 1; i < Size+1; i++) {
			int heldValue = -1;
			while(heldValue<1) {
			int calculatedVariance = (int) (Math.random() * ( 2.2 * variance ) -  variance);
			heldValue = masterMap.get(i-1) +  (int)(calculatedVariance*cashMultiplier) ;
			}
			masterMap.put(i,heldValue);
		}
		return masterMap;
	}

}
