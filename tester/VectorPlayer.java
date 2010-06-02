package tester;

import java.awt.geom.Area;

import kobaltBasic.KeyboardSupport;
import kobaltBasic.RotatingSpriteActor;
import kobaltBasic.Sprite;



public class VectorPlayer extends RotatingSpriteActor implements KeyboardSupport {

	public VectorPlayer(Sprite s, Area vectorSprite, double x, double y) {
		super(s, x, y);
		
		this.setBounds(vectorSprite);
	}

	public void handleKeyPress(int keyCode) {
	
		if(keyCode == UP) {
			this.heading = 0;
			this.velocity = -100; // if the up button is pressed set negative velocity to go up
		}
		
		if(keyCode == DOWN) {
			this.heading = 0;
			this.velocity = 100; // if down button is pressed positive velocity to go down
		}
		
		if(keyCode == LEFT) {
			this.heading = -90;
			this.velocity = 100;
		}
		if(keyCode == RIGHT) {
			this.heading = 90;
			this.velocity = 100;
		}
		
	}

	public void handleKeyRelease(int keyCode) {
		if(keyCode == UP) this.velocity = 0; // if the up button is pressed set negative velocity to go up
		
		if(keyCode == DOWN) this.velocity = 0; // if down button is pressed positive velocity to go down
		
		if(keyCode == LEFT) this.velocity = 0;
		if(keyCode == RIGHT) this.velocity = 0;
		
	}

}
