package tester;

import java.awt.Graphics2D;

import kobaltBasic.BasicGame;
import effects.TwirlEffect;

public class TwirlTester extends BasicGame {
	
	
	SpaceBG bg;
	TwirlEffect effect;

	public TwirlTester(String title, int w, int h) {
		super(title, w, h);

		bg = new SpaceBG();
		effect = new TwirlEffect(350,100);
		
	}


	public void render(Graphics2D g) {

		bg.draw(g);
		effect.applyEffect(g);
		
		
	}

	public void update() {
		effect.update();
	}

		
	
	public static void main(String[] args) {
		new TwirlTester("Twirl Tester",640,480).start(); // creates a new game window of size 640x480 and starts the game loop.

	}

}
