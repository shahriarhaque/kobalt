package particle;

import java.awt.Color;
import java.awt.Graphics2D;

import kobaltBasic.BasicGame;

public abstract class Particle {
	
	public double x;
	public double y;
	
	/** X and Y components of the velocity in pixels/second. **/
	public double vx;
	public double vy;

	/** Color of the particle. **/
	public Color color;
	
	/** Age of the particle in milliseconds. **/
	public int age = 0;
	
	/** Lifespan of the particle in milliseconds.**/
	public int lifeSpan = 0;
	
	/** Radius of the particle in pixels.**/
	public int size;
	
	public abstract void render(Graphics2D g);
	
	public void update(){
		double fraction = BasicGame.interval / 1000.0f;
		
		x += (vx*fraction) ;
		y += (vy*fraction);
	}
	

}
