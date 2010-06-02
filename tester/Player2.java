package tester;

import kobaltBasic.KeyboardSupport;
import kobaltBasic.Sprite;
import kobaltBasic.SpriteActor;

/*
 * Implementing ControlledByKeyboard lets this entity respond to keyboard button presses.
 */

public class Player2 extends SpriteActor implements KeyboardSupport {



	
	
	/*
	 * Sprite s: The sprite that this game entity will use, can contain multiple frames
	 * posX: X position of the entity
	 * posY: Y position of the entity
	 */
	public Player2(Sprite s, int posX, int posY) {
		super(s, posX, posY);
		x = posX;
		y = posY;
	
	}
	
	public void update(){
		
		if (x < 0) x = 300;
		
		
		super.update();

	}

	public void handleKeyPress(int keyCode) {
		
		if(keyCode == UP) this.y_velocity = -100; // if the up button is pressed set negative velocity to go up
		
		if(keyCode == DOWN) this.y_velocity = 100; // if down button is pressed positive velocity to go down
		
		if(keyCode == LEFT) this.x_velocity = -200;
		if(keyCode == RIGHT) this.x_velocity = 200;
		
	}

	public void handleKeyRelease(int keyCode) {
		this.y_velocity = 0; // when no key is being pressed set velocity to zero, otherwise the bar floats off the screen
		this.x_velocity = 0;

	}


}
