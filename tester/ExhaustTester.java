package tester;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kobaltBasic.BasicGame;
import kobaltBasic.KeyboardSupport;
import kobaltBasic.Sprite;
import kobaltBasic.Utilities;

public class ExhaustTester extends BasicGame implements KeyboardSupport {
	
	Player1 p1;

	public ExhaustTester(String title, int w, int h) {
		super(title, w, h);
		
		BufferedImage img = Utilities.load("tester/resources/pixelship.gif");
		BufferedImage grad = Utilities.load("tester/resources/grad.gif");
		BufferedImage ship = Utilities.applyAlphaMask(img, grad);
		
		p1 = new Player1(new Sprite(ship),300,300); // creates a player object with a sprite consisting of a single frame

		addKeyboardSupport(this); // makes the game screen responds to keyboard button events
		
	}


	public void render(Graphics2D g) {
		
		
		fillWithColor(Color.BLACK); // paints the background black

		p1.render(g);
		
		
	}

	public void update() {
		p1.update(); // this forces player1 to update its x,y coordinates based on its velocities.
		
	}
	

	public void handleKeyPress(int keyCode) {
		p1.handleKeyPress(keyCode);	// informs player1 that the given key has been pressed. Similar method in Player1 decides how to deal with it
	}


	public void handleKeyRelease(int keyCode) {
		p1.handleKeyRelease(keyCode);	// same as previous method
	}
	
	public static void main(String[] args) {
		new ExhaustTester("Exhaust Testers",640,480).start(); // creates a new game window of size 640x480 and starts the game loop.

	}

}
