package tester;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import experimental.lighting.Illuminator;
import experimental.lighting.LightSource;

import kobaltBasic.BasicGame;
import kobaltBasic.KeyboardSupport;
import kobaltBasic.Sprite;
import kobaltBasic.Utilities;

public class LightingTester extends BasicGame implements KeyboardSupport {
	
	Player2 p;
	LightSource light;
	Illuminator lumin;

	
	public LightingTester(String title, int w, int h) {
		super(title, w, h);
		
		BufferedImage img = Utilities.load("tester/resources/player.png");
		
		p = new Player2(new Sprite(img),300,300);

		BufferedImage b1 = Utilities.load("tester/resources/sun.png");
		BufferedImage b2 = Utilities.load("tester/resources/sun-mask.png"); 
		light = new LightSource(Utilities.applyAlphaMask(b1, b2));
		light.x = 300;
		light.y = 10;
		light.range = 300;
		
		
		lumin = new Illuminator();
		lumin.source = light;
		lumin.target = p;
		
		PongIntro iScreen = new PongIntro();
		screenList.add(iScreen);
		currentScreen = 0;
		addKeyboardSupport(this); // makes the game screen responds to keyboard button events
		

		
		
	}


	public void render(Graphics2D g) {
		
		
		fillWithColor(Color.BLACK); // paints the background black

		lumin.render(g);
		
		
	}

	public void update() {
		p.update();
	}
	

	public void handleKeyPress(int keyCode) {
		p.handleKeyPress(keyCode);
	}


	public void handleKeyRelease(int keyCode) {
		p.handleKeyRelease(keyCode);
	}
	
	public static void main(String[] args) {
		new LightingTester("Lighting Effects Demo",640,480).start(); // creates a new game window of size 640x480 and starts the game loop.

	}

}
