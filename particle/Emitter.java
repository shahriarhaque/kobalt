package particle;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import kobaltBasic.BasicGame;

public abstract class Emitter {

	public int sourceX;
	public int sourceY;

	/** Acceleration in the X and Y directions (in pixel/second^2) **/
	public int accX = 0;
	public int accY = 0;

	/** The array that holds all the particles **/
	public Particle[] pArray;

	/**
	 * Initializes all the particles. Called every time a particle system is reset.
	 */
	public abstract void initParticles();

	/** 
	 * Initializes the particle at the given index of the particle array {@code pArray}.
	 * @param i Index of the particle within the array.
	 */
	public abstract void initParticle(int i);


	public void update(){

		double fraction = 0;


		for(int i = 0; i < pArray.length; i++){
			Particle p = pArray[i];
			p.age += BasicGame.interval; // age the particle
			
			fraction = (p.age / 1000.0); // calculate the age in terms on ONE second

			if(p.age >= 0){ // move the particle only when it is of positive age
				
				// calculate how much acceleration is in effect at this age
				p.vx += accX*fraction;
				p.vy += accY*fraction;
				
				// updates the position based on the current x and y velocities and age
				p.update();
			}

			// if particle has died, respawn it
			if(p.age >= p.lifeSpan) initParticle(i);

		}




	}

	public void render(Graphics2D g){


		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		for(int i = 0; i < pArray.length; i++){
			Particle p = pArray[i];

			if(p.age >= 0){
				g.setColor(p.color);
				p.render(g);

			}


		}




	}


}
