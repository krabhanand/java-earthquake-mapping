package module6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class WorldPovertyMap extends PApplet {
	// You can ignore this.  It's to get rid of eclipse warnings
		private static final long serialVersionUID = 1L;

		// IF YOU ARE WORKING OFFILINE, change the value of this variable to true
		private static final boolean offline = false;
		
		/** This is where to find the local tiles, for working without an Internet connection */
		public static String mbTilesString = "blankLight-1-3.mbtiles";
		
		int x,y;//for colours settings
		//file to get data from
		private String povertyFile = "csvjson.json";

		//map library
		private UnfoldingMap map;
		
		// Markers for each city
		private List<Marker> povertyMarkers;
		// A List of country markers
		private List<Marker> countryMarkers;

		private String countryFile= "countries.geo.json";
		
		public void setup() {		
			// (1) Initializing canvas and map tiles
			size(900, 700, JAVA2D);
			if (offline) {
			    map = new UnfoldingMap(this, 200, 50, 650, 600, new MBTilesMapProvider(mbTilesString));
			   // earthquakesURL = "2.5_week.atom";  // The same feed, but saved August 7, 2015
			}
			else {
				map = new UnfoldingMap(this, 200, 50, 650, 600, new Microsoft.HybridProvider());
				// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			    //earthquakesURL = "2.5_week.atom";
			}
			MapUtils.createDefaultEventDispatcher(this, map);
			
			
			  //     STEP 1: load country features and markers
			List<Feature> countries = GeoJSONReader.loadData(this, countryFile= "countries.geo.json");
			countryMarkers = MapUtils.createSimpleMarkers(countries);
			FileReader reader = null;
			povertyMarkers=new ArrayList<Marker>();
			try {
				 reader = new FileReader(povertyFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray jr=new JSONArray(reader);
			for(int i=0;i<jr.size();i++) {
			JSONObject jr0=jr.getJSONObject(i);
			System.out.println("Country Name: "+jr0.getString("TableName",""));
			for(Marker m:countryMarkers) {
				if(m.getClass()==MultiMarker.class) {
					MultiMarker mm=(MultiMarker)m;
					if(((String)(mm.getProperty("name"))).equals(jr0.getString("TableName"))) {
						for(Marker cm:mm.getMarkers()) {
							Marker pm=new PovertyMarker(cm.getLocation());
							((PovertyMarker)pm).colour=getColour(jr0.getString("IncomeGroup"));
							try{povertyMarkers.add(pm);}
							catch(Exception e) {
								System.out.println(e);
							}
							System.out.print(pm.getLocation()+" "+((PovertyMarker)pm).colour+" ");
						}System.out.println();	
					}
				}
			}
		}
			
			 map.addMarkers(povertyMarkers);
			
		}
		

		private int getColour(String string) {
			// TODO Auto-generated method stub
			if(string.equals("High income")) return color(255,0,0);
			if(string.equals("Low income")) return color(0,255,0);
			if(string.equals("Lower middle income")) return color(255,255,0);
			if(string.equals("Upper middle income")) return color(0,0,255);
			
			return 0;
		}


		public void draw() {
			background(0);
			map.draw();
			addKey();
			
		}


		private void addKey() {
			// TODO Auto-generated method stub
			fill(255, 250, 240);
			
			int xbase = 25;
			int ybase = 50;
			
			rect(xbase, ybase, 200, 250);
			
			fill(0);
			textAlign(LEFT, CENTER);
			textSize(12);
			text("Income / Poverty Map Key", xbase+25, ybase+25);
			x=40;y=100;
			fill(color(255,0,0));
			ellipse(x, y+20, 5, 5);
			fill(color(0,255,0));
			ellipse(x, y+40, 5, 5);
			fill(color(255,255,0));
			ellipse(x, y+60, 5, 5);
			fill(color(0,0,255));
			ellipse(x, y+80, 5, 5);
		
			//text
			fill(0,0,0);
			text("High income",x+20,y+=20);
			text("Low income",x+20,y+=20);
			text("Lower middle income",x+20,y+=20);
			text("Upper middle income",x+20,y+=20);
		}
}
