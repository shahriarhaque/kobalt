package tester;

import java.awt.Color;
import java.awt.Graphics2D;

import kobaltBasic.BasicGame;
import kobaltBasic.MouseSupport;
import kobaltBasic.GameScreen;

public class PongIntro extends GameScreen implements MouseSupport {
	
	CustomButton cbtn;
	SpaceBG bg;
	
	int delay = 1000;
	int elapsed = 0;
	
	static boolean fadeOut = false;

	
	public PongIntro(){
		addMouseSupport(this);
		cbtn = new CustomButton("Click Me", 250,200,150,50);
		bg = new SpaceBG();
	
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, 640, 480);
		bg.draw(g);
		
		elapsed += BasicGame.interval;
		
		if(elapsed >= delay) elapsed = delay;
		
		if(elapsed >= delay) {
			 cbtn.render(g);
		}
		
		if(fadeOut){
			
			 BasicGame.currentScreen = 0;
		}
		
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void handleMouseClick(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void handleMouseMove(int x, int y) {
		cbtn.handleMouseMove(x, y);
		
	}

	public void handleMousePress(int x, int y) {
		cbtn.handleMousePress(x, y);
		
	}

	public void handleMouseRelease(int x, int y) {
		cbtn.handleMouseRelease(x, y);
		
	}

}
