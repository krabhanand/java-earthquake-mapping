package anand;

import processing.core.PApplet;
import processing.core.PImage;
import java.time.LocalTime;

public class AnandApplet extends PApplet {
	private PImage img;
	private String URL="https://processing.org/img/processing-web.png";
	public void setup() {
		//set up image and screen
	size(200,200);	//canvas size setup
	background(255,255,0);
	img = loadImage(URL,"png");//image stored in img variable, not displayed yet
	}
	public void draw() {
		//runs all the time, image and others displayed here
		img.resize(0, height);//resize image height to fit canvas, width to accomodate height as per image ratio
		image(img,0,0);	//image drawn at coordinated starting 0,0
		int hour = java.time.LocalTime.now().getHour();//get local time
		if(hour==6 || hour==18)  fill(204,204,0);//evening and morning sun color
		else if(hour<5 || hour>18) fill(153,153,102);//nigh sun colour
		else
		fill(255,209,0);//day sun colour
		ellipse(width/4,height/5,width/5,height/5);//sun circle shape
		
		arc(90,140,85,45,0,PI);
	}
}
