package tester;

import java.awt.Color;

import particle.Emitter;
import particle.Particle;

import kobaltBasic.Utilities;

public class ExhaustEmitter extends Emitter {

	
	public double DY = 0;
	public int pSize = 12;
	

	
	public Color[] cArray = { 
			new Color(24,57,247)
	};
	
	
	public ExhaustEmitter(Particle[] pArray, int sx, int sy){
		sourceX = sx;
		sourceY = sy;
		this.pArray = pArray;
		
	}
	
	public void reset(){
		for(int i=0; i < pArray.length; i++) initParticle(i);
	}
	
	
	@Override
	public void initParticle(int i) {
		Particle p = pArray[i];
		
		p.x = sourceX;
		p.y = sourceY;
		p.color = cArray[0];
		
		p.age = -(int) (Math.random()*500); //this ensures a delay between the launch of particles
		
		p.lifeSpan = 150; // each particle has a lifespan of 250 milliseconds
		
		p.size = pSize;//(int) (Math.random()*pSize)+1; // randomly assign a size

		
		p.vx = Utilities.getRandomSign()*(Math.random()*75);
		p.vy = DY;
		
		
		
	}
	


	
	
	@Override
	public void initParticles() {
		int numParticles = pArray.length;
		
		for(int i = 0; i < numParticles; i++) initParticle(i);
		
		accX = 0;
		accY = 0;
		
	}
	


}
