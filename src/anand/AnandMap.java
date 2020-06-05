package anand;

import java.util.List;
import java.util.ArrayList;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import de.fhpotsdam.unfolding.providers.Microsoft;



public class AnandMap extends PApplet{



		// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
		private static final boolean offline = false;
		

		
		// The map
		private UnfoldingMap map;

	
		public void setup() {
			size(950, 600, JAVA2D);//set canvas specification
		    background(10);//set background to this color
			map=new UnfoldingMap (this,0,0,400,300,new Microsoft.HybridProvider());//assign
			map.zoomToLevel(1);//zoom in map
			MapUtils.createDefaultEventDispatcher(this, map);//bring map to life: scroll, zoom in out
			
		
			//ADDING LOCATIONS TO BE MARKED
			Location valLock=new Location(-38.14f,-73.03f);//location object of v earthquake
			//Location objects containing locations of other earthquakes
			Location alaskaLoc= new Location(64.20f,-139.49f);
			Location sumatraLoc=new Location(-0.589f,101.34f);
			Location tokyoLoc=new Location(35.676f,139.65f);
			Location kamchatkaLoc=new Location(64.43f,166.784f);
			//Adding properties to valLock via Feature class
			PointFeature valEq=new PointFeature(valLock);
			PointFeature alaskaEq=new PointFeature(alaskaLoc);
			PointFeature sumatraEq=new PointFeature(sumatraLoc);
			Feature tokyoEq=new PointFeature(tokyoLoc);
			Feature kamchatkaEq=new PointFeature(kamchatkaLoc);
			//adding properties that will be returned in form of Hashmaps
			valEq.addProperty("title", "Valdiva, Chile");
			valEq.addProperty("magnitude", "9.6");
			valEq.addProperty("date", "May 22, 1960");
			valEq.addProperty("year", "1960");
			
			alaskaEq.addProperty("title", "Alaska, USA");
			alaskaEq.addProperty("magnitude", "9.6");
			alaskaEq.addProperty("date", "May 22, 1958");
			alaskaEq.addProperty("year", "1958");
			
			sumatraEq.addProperty("title", "Sumatra, Indonesia");
			sumatraEq.addProperty("magnitude", "9.6");
			sumatraEq.addProperty("date", "May 22, 2004");
			sumatraEq.addProperty("year", "2004");
			
			tokyoEq.addProperty("title", "Tokyo, Japan");
			tokyoEq.addProperty("magnitude", "9.6");
			tokyoEq.addProperty("date", "May 22, 1979");
			tokyoEq.addProperty("year", "1979");
			
			kamchatkaEq.addProperty("title", "Kamchatka");
			kamchatkaEq.addProperty("magnitude", "9.6");
			kamchatkaEq.addProperty("date", "May 22, 2001");
			kamchatkaEq.addProperty("year", "2001");
			
			//Now, all earthquakes location are added in a list
			List <PointFeature> bigEqs = new ArrayList<PointFeature>();
			bigEqs.add(valEq);
			bigEqs.add(alaskaEq);
			bigEqs.add(sumatraEq);
			bigEqs.add((PointFeature) tokyoEq);
			bigEqs.add((PointFeature) kamchatkaEq);
			List<Marker> markers = new ArrayList<Marker>();
			int yellow=color(255,255,0);
			int black=color(0,255,0);
			for(PointFeature eq: bigEqs) {
				markers.add(new SimplePointMarker(eq.getLocation(),eq.getProperties()));
			}
			for(Marker mark:markers) {//System.out.println("before if");
				if(Integer.parseInt((String) mark.getProperty("year"))>2000)
					{//System.out.println("inside if");
					mark.setColor(yellow);
					//System.out.println("yellow set");}
					}
				else {mark.setColor(black);
				//System.out.println("yellow set");
				}
				
				map.addMarker(mark);//attach the marker into this map
			}
			
			

			
		}
		public void draw() {
		    map.draw();//draw this map
		    addKey();//display markers of magnitude
		}
		private void addKey()
		{//interpret and display markers representing earthquake magnitudes
			
		}
}
