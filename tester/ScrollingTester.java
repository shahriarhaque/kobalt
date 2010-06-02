package tester;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import kobaltBasic.BasicGame;
import kobaltBasic.KeyboardSupport;
import kobaltBasic.ScrollingEntity;
import kobaltBasic.Sprite;
import kobaltBasic.Utilities;

public class ScrollingTester extends BasicGame implements KeyboardSupport {
	
	Player1 p1;
	ScrollingEntity bg;

	public ScrollingTester(String title, int w, int h) {
		super(title, w, h);
		
		BufferedImage img = Utilities.load("tester/resources/player.png");
		BufferedImage img2 = Utilities.load("tester/resources/sky2.png");
		
		p1 = new Player1(new Sprite(img),300,300); // creates a player object with a sprite consisting of a single frame
		bg = new ScrollingEntity(new Sprite(img2), 0, 0, w, h);
		
		bg.y_velocity = 100;
		addKeyboardSupport(this); // makes the game screen responds to keyboard button events
		
	}


	public void render(Graphics2D g) {
		
		
		//fillWithColor(Color.BLACK); // paints the background black
		bg.render(g);

		p1.render(g);
		
		
		
	}

	public void update() {
		p1.update(); // this forces player1 to update its x,y coordinates based on its velocities.
		bg.update();
	}
	

	public void handleKeyPress(int keyCode) {
		//p1.handleKeyPress(keyCode);	// informs player1 that the given key has been pressed. Similar method in Player1 decides how to deal with it
		if(keyCode == KeyEvent.VK_LEFT){
			bg.x_velocity = 100;
		}
		
		if(keyCode == KeyEvent.VK_RIGHT){
			bg.x_velocity = -100;
		}
		
		if(keyCode == KeyEvent.VK_UP){
			bg.y_velocity = 100;
		}
		
		if(keyCode == KeyEvent.VK_DOWN){
			bg.y_velocity = -100;
		}
	}


	public void handleKeyRelease(int keyCode) {
		//p1.handleKeyRelease(keyCode);	// same as previous method
		if(keyCode == KeyEvent.VK_LEFT){
			bg.x_velocity = 0;
		}
		
		if(keyCode == KeyEvent.VK_RIGHT){
			bg.x_velocity = 0;
		}
		
		if(keyCode == KeyEvent.VK_UP){
			bg.y_velocity = 0;
		}
		
		if(keyCode == KeyEvent.VK_DOWN){
			bg.y_velocity = 0;
		}
	}
	
	public static void main(String[] args) {
		new ScrollingTester("Exhaust Testers",640,480).start(); // creates a new game window of size 640x480 and starts the game loop.

	}

}
