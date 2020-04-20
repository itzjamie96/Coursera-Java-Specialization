package module3;

import processing.core.PApplet;

public class SmileyFace extends PApplet{

	public void setup() {
		size(400,400);
		background(200,200,200);
	}
	
	public void draw() {
		//face
		fill(255,255,0);
		ellipse(200,200,390,390);
		
		//eyes
		fill(0,0,0);		
		ellipse(120, 140, 50, 70);
		ellipse(270, 140, 50, 70);
		
		//smile mouth
		noFill();
		arc(200,280,150,100,0,PI);
		
	}
	
	
}
