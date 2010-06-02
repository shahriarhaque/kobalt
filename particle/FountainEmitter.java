package particle;

import java.awt.Color;

public class FountainEmitter extends Emitter {

	
	public int initDX = 150;
	public int initDY = -250;
	public int pSize = 10;
	
	public Color[] cArray = null;
	
	
	public FountainEmitter(Particle[] pArray, int sx, int sy){
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
		p.color = cArray[0]; // use the first color of the color array
		
		p.age = -(int) (Math.random()*1000); //this ensures a delay between the launch of particles
		
		p.lifeSpan = 2000; // each particle has a lifespan of 2 seconds
		
		p.size = (int) (Math.random()*pSize)+1; // randomly assign a size
		
		
		// split the flow between left and right with a 50% probability
		int num = (int) (Math.random()*50);
		num = num%2;
		
		if(num == 0) initLeftFlow(p);	
		else initRightFlow(p);
		
		
	}
	
	private void initLeftFlow(Particle p){

		p.vx = -(int) (Math.random()*initDX);
		p.vy = initDY;
	}
	
	private void initRightFlow(Particle p){

		p.vx = (int) (Math.random()*initDX);
		p.vy = initDY;
	}

	
	
	@Override
	public void initParticles() {
		
		for(int i = 0; i < (pArray.length/2); i++){
			Particle p = pArray[i];
			initLeftFlow(p);
			pArray[i] = p;
		}
		
		for(int i = (pArray.length/2); i < pArray.length; i++){
			Particle p = pArray[i];
			initRightFlow(p);
			pArray[i] = p;
		}
		
		accX = 0;
		accY = 15; // positive value to simulate gravity
		
	}
	
	public void update(){
		super.update();
		if(cArray == null) return;
		
		/* For each particle, calculate the age in terms of its lifespan, use the ratio
		 * to pick the right color from the color array.
		 */
		for(Particle p: pArray){
			if(p.age >= 0)
			p.color = cArray[(int)((p.age/ (double) p.lifeSpan)*(cArray.length-1))];
		}
	}
	


}
