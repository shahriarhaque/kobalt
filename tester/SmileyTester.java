package tester;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;

import kobaltBasic.BasicGame;
import kobaltBasic.KeyboardSupport;
import kobaltBasic.Sprite;
import kobaltBasic.Utilities;

public class SmileyTester extends BasicGame implements KeyboardSupport{

	Smiley s;
	SmileyPhysics sp;
	Body sbody;
	
	public SmileyTester(String title, int w, int h) {
		super(title, w, h);
		
		sp = new SmileyPhysics();
		sp.init();
		sbody = sp.body2;
		
		
		s = new Smiley(new Sprite(Utilities.load("tester/resources/smiley.png")), sp.body2);
		
		addKeyboardSupport(this);
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SmileyTester("Smiley Tester",640,480).start();

	}

	@Override
	public void render(Graphics2D g) {
		fillWithColor(WHITE);
		s.render(g);
		sp.render(g); // debugging
		
	}

	@Override
	public void update() {
		sp.update();
		s.update();
		
	}

	
	static final Vector2f up = new Vector2f(0,-2000);
	static final Vector2f left = new Vector2f(-2000, 0);
	static final Vector2f right = new Vector2f(2000 ,0);
	
	public void handleKeyPress(int keyCode) {
		
		if(keyCode == KeyEvent.VK_UP) sbody.addForce(up);
		if(keyCode == KeyEvent.VK_LEFT) sbody.addForce(left);
		if(keyCode == KeyEvent.VK_RIGHT) sbody.addForce(right);
		
	}

	public void handleKeyRelease(int keyCode) {
		
		
	}


}
