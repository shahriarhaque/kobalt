package tester;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import effects.Drawable;

import kobaltBasic.Utilities;



public class SpaceBG implements Drawable {
	
	BufferedImage bg;
	
	public SpaceBG(){
		bg = Utilities.load("tester/resources/horsehead-nebula.jpg");
	}
	
	public void draw(Graphics2D g){
		
		g.drawImage(bg,0,0,null);
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return bg.getHeight();
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return bg.getWidth();
	}

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
