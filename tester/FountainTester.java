package tester;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import kobaltBasic.BasicGame;
import kobaltBasic.KeyboardSupport;
import kobaltBasic.Utilities;
import particle.FadingParticle;
import particle.FountainEmitter;
import particle.Particle;

public class FountainTester extends BasicGame implements KeyboardSupport {


	FountainEmitter f;


	public FountainTester(String title, int w, int h, boolean fullScreen) {
		super(title, w, h, fullScreen);

		addKeyboardSupport(this);

		Particle[] pArray = new FadingParticle[700];
		for(int i = 0; i < 700; i++) pArray[i] = new FadingParticle();

		f =  new FountainEmitter(pArray, 300, 300);
		f.cArray = Utilities.getVerticalColors("tester/resources/blue-grad.gif", 10);
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
		
		
		new FountainTester("Fountain Effect Demo", 640,480, false).start();

	}



	public void handleKeyPress(int keyCode) {}



	public void handleKeyRelease(int keyCode) {
		if(keyCode == KeyEvent.VK_ENTER) f.reset();

	}

}
