package module6;

import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PGraphics;

public class PovertyMarker extends CommonMarker {

	int colour=11;
	public PovertyMarker(Location location) {
		super(location);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub
		pg.fill(colour);
		pg.ellipse(x, y, 5, 5);
		
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub

	}

}
