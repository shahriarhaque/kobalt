package tester;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import kobaltBasic.KeyboardSupport;
import kobaltBasic.Sprite;
import kobaltBasic.SpriteActor;
import particle.MagicParticle;
import particle.Particle;
import effects.FadeEffect;

/*
 * Implementing ControlledByKeyboard lets this entity respond to keyboard button presses.
 */

public class Player1 extends SpriteActor implements KeyboardSupport {


	static final int space = KeyEvent.VK_SPACE;
	
	boolean fade = false;
	FadeEffect f1 = new FadeEffect(FadeEffect.FADE_OUT);
	
	ExhaustEmitter e;
	
	
	/*
	 * Sprite s: The sprite that this game entity will use, can contain multiple frames
	 * posX: X position of the entity
	 * posY: Y position of the entity
	 */
	public Player1(Sprite s, int posX, int posY) {
		super(s, posX, posY);
		
		int numPart = 20;
		Particle[] array = new Particle[numPart];
		for(int i = 0; i < array.length; i++) array[i] = new MagicParticle();
		e = new ExhaustEmitter(array, posX+(width/2-6), posY+(height/2+13));
	
	}
	
	
	public void update(){
		
		if (x < 0) x = 300;
		
		
		super.update();
		
		e.sourceX = (int)this.x + (width/2-6) ;
		e.sourceY = (int)this.y + (height/2+13);
		e.DY = -(this.y_velocity-50);
		
		e.update();
	}

	public void handleKeyPress(int keyCode) {
		
		
		
		if(keyCode == UP) this.y_velocity = -200; // if the up button is pressed set negative velocity to go up
		
		if(keyCode == DOWN) this.y_velocity = 200; // if down button is pressed positive velocity to go down
		
		if(keyCode == LEFT) this.x_velocity = -200;
		if(keyCode == RIGHT) this.x_velocity = 200;

		
	}

	public void handleKeyRelease(int keyCode) {
		this.y_velocity = 0; // when no key is being pressed set velocity to zero, otherwise the bar floats off the screen
		this.x_velocity = 0;
		
		if(keyCode == space) {
			fade = !fade;
			f1.reset();
		}
		
	}
	
	public void render(Graphics2D g){
		if(fade) {
//			BlurEffect.applyEffect(g, this);
			f1.applyEffect(g, this);
			fade = !f1.done;
			
		}
		else super.render(g);
		
		e.render(g);
	}


}
