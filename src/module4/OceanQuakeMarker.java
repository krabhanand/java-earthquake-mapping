package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;
import java.time.LocalDate;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
float SIZER=(float) 5;
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = false;

	}

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Drawing a centered square for Ocean earthquakes
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		pg.rect(x-radius/2, y-radius/2, radius, radius);
		if(date.equals(pastDay) || date.equals(pastHour))
			{pg.line(x-SIZER, y-SIZER, x+SIZER, y+SIZER);
			pg.line(x+SIZER, y-SIZER, x-SIZER, y+SIZER);}
		
	}
	


	

}
