package tester;

import java.awt.Graphics2D;

import effects.WaterRippleEffect;

import kobaltBasic.BasicGame;

public class RippleTester extends BasicGame {
	
	
	SpaceBG bg;
	WaterRippleEffect effect;

	public RippleTester(String title, int w, int h) {
		super(title, w, h);

		bg = new SpaceBG();
		effect = new WaterRippleEffect(350,100);
		
	}


	public void render(Graphics2D g) {
		
	
		bg.draw(g);
		effect.applyEffect(g);
		
		
	}

	public void update() {
		effect.update();
	}

		
	
	public static void main(String[] args) {
		new RippleTester("Ripple Tester",640,480).start(); // creates a new game window of size 640x480 and starts the game loop.

	}

}
