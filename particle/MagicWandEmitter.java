package particle;

import java.awt.Color;

import kobaltBasic.Utilities;

public class MagicWandEmitter extends Emitter {

	
	public int initDX = 150;
	public int initDY = -250;
	public int pSize = 18;
	
	public Color[] cArray = { 
			new Color(80,234,252), // light blue
			new Color(255,106,106), // red
			new Color(79,79,240), // dark blue
			new Color(241,255,124), // yellow
			new Color(141,255,141), // green
			new Color(255,114,253) // purple
	};
	
	
	public MagicWandEmitter(Particle[] pArray, int sx, int sy){
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
		p.color = cArray[ (int) (Math.random()*cArray.length) ];
		
		p.age = -(int) (Math.random()*500); //this ensures a delay between the launch of particles
		
		p.lifeSpan = 250; // each particle has a lifespan of 250 milliseconds
		
		p.size = (int) (Math.random()*pSize)+1; // randomly assign a size

		
		p.vx = Utilities.getRandomSign()*(Math.random()*200);
		
		p.vy = Utilities.getRandomSign()*(Math.random()*200);
		
		
		
	}
	


	
	
	@Override
	public void initParticles() {
		int numParticles = pArray.length;
		
		for(int i = 0; i < numParticles; i++) initParticle(i);
		
		accX = 0;
		accY = 0;
		
	}
	


}
