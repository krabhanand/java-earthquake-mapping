package anand;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;

import processing.core.PApplet;

public class LifeExpectancy extends PApplet{
UnfoldingMap map;
Map<String,Float> lifeExpByCountry;
List<Feature> lifeExpCountries;
List<Marker> lifeExpMarker;
public void setup() {
	//specify canavas size, tech
	size(800,600,JAVA2D);

	//create map object specifying coords, size, provider of map
	map= new UnfoldingMap(this,0,0,600,600,new Microsoft.HybridProvider());
	
	//bring map to life: scroll, zoom in out
	MapUtils.createDefaultEventDispatcher(this, map);
	
	//load life expectancy data in following map(hashmap)
	lifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBank.csv");
	Iterator it = lifeExpByCountry.entrySet().iterator();
		//print country and life exp data
	while(it.hasNext()) {try {
		 Map.Entry mapElement = (Map.Entry)it.next(); 
         float marks = (float) mapElement.getValue() ; 
         System.out.println(mapElement.getKey() + " : " + marks);
         }
	catch(Exception e) {
		System.out.println("in printing life expectancy, "+e);
	}
	}
	
	}
	


//method to return life expectancy hashmap from csv file
private Map<String, Float> loadLifeExpectancyFromCSV(String fileName) {

	//map to return
	Map<String, Float> lifeExpMap=new HashMap<String, Float>();
	
	//load all rows
	String rows[]=loadStrings(fileName);
	//System.out.println(rows[0]);
	
	//parse each row, pick up 6th coloumn as float and 5th coloumn as string
	for(String row: rows) {
		//save data of each row in an an array of strings
		String[] coloumns= row.split(",");
		
		//do the parsing of the row
		Float f = (float) 1.0;
		if(coloumns.length>5) {
			System.out.println("inside if"+ coloumns[4]+" "+coloumns[5]);
			try{Float value = Float.parseFloat(coloumns[5]);
			//put country name and life span in map
			lifeExpMap.put(coloumns[4],value);}
			catch(Exception e) {
				continue;
			}
		}
		
	}
	return lifeExpMap;
}

public void draw() {
	map.draw();
}

}
