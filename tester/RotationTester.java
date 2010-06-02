package tester;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kobaltBasic.BasicGame;
import kobaltBasic.KeyboardSupport;
import kobaltBasic.Sprite;
import kobaltBasic.Utilities;

public class RotationTester extends BasicGame implements KeyboardSupport {
	
	Spaceship p1;

	public RotationTester(String title, int w, int h) {
		super(title, w, h);
		
		BufferedImage img = Utilities.load("tester/resources/powergenerator.gif");
		
		p1 = new Spaceship(new Sprite(img),300,100); // creates a player object with a sprite consisting of a single frame

		p1.rotational_velocity = 10;
		
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
		new RotationTester("Exhaust Testers",640,480).start(); // creates a new game window of size 640x480 and starts the game loop.

	}

}
