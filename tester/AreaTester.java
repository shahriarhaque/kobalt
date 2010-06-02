package tester;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

import kobaltBasic.BasicGame;
import kobaltBasic.KeyboardSupport;
import kobaltBasic.Sprite;
import kobaltBasic.Utilities;

public class AreaTester extends BasicGame implements KeyboardSupport {

	VectorPlayer player1;
	VectorPlayer player2;
	
	
	public AreaTester(String title, int w, int h) {
		super(title, w, h);
		
		addKeyboardSupport(this);
		
		int x[] = new int[] { 25, 47, 1 }; // 25, 47, 1
		int y[] = new int[] {8, 32, 32 }; // 8, 32, 32
		Polygon p = new Polygon(x,y,3);
		Sprite s = new Sprite(Utilities.load("tester/resources/player.png"));
		player1 = new VectorPlayer(s, new Area(p), 100, 100);
		player2 = new VectorPlayer(s, new Area(p), 300, 300);

		
		player1.drawVector = true;
		player2.drawVector = true;
		
		player1.rotational_velocity = 40;
		
	}

	@Override
	public void render(Graphics2D g) {
		fillWithColor(BLACK);
		player1.render(g);
		player2.render(g);
		
	}

	@Override
	public void update() {
		player1.update();
		player2.update();
		
	}
	
	public static void main(String[] args){
		new AreaTester("Area Tester",640,480).start();
	}

	public void handleKeyPress(int keyCode) {
		
		player2.handleKeyPress(keyCode);
		if(keyCode == KeyEvent.VK_ENTER){
			System.out.println(player1.collides(player2));
		}
	}

	public void handleKeyRelease(int keyCode) {
		player2.handleKeyRelease(keyCode);
		
	}

}
