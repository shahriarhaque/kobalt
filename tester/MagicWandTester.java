package tester;

import java.awt.Graphics2D;

import kobaltBasic.BasicGame;
import kobaltBasic.MouseSupport;
import particle.MagicParticle;
import particle.MagicWandEmitter;
import particle.Particle;

public class MagicWandTester extends BasicGame implements MouseSupport {


	MagicWandEmitter f;

	public MagicWandTester(String title, int w, int h) {
		super(title, w, h);

		addMouseSupport(this);

		
		int numParticles = 100;
		Particle[] pArray = new MagicParticle[numParticles];
		for(int i = 0; i < numParticles; i++) pArray[i] = new MagicParticle();

		f =  new MagicWandEmitter(pArray, 300, 300);
		f.initParticles();
	}



	@Override
	public void render(Graphics2D g) {
		fillWithColor(BLACK);
		f.render(g);

	}

	@Override
	public void update() {
		f.update();
	}

	public static void main(String[] args) {
		new MagicWandTester("Magic Wand Demo", 640,480).start();

	}



	public void handleMouseClick(int x, int y) {}



	public void handleMouseMove(int x, int y) {
		if(f != null){
			f.sourceX = x;
			f.sourceY = y;
		}

	}



	public void handleMousePress(int x, int y) {}

	public void handleMouseRelease(int x, int y) {}


}
