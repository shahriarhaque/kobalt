package tester;

import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kobaltBasic.BasicGame;
import kobaltBasic.Utilities;
import effects.BlendComposite;

public class MaskTester extends BasicGame {

	BufferedImage img1;
	BufferedImage msk;

	public MaskTester(String title, int w, int h) {
		super(title, w, h);
		
		img1 = Utilities.load("tester/resources/starburst.gif");
		BufferedImage temp;
		
		msk = Utilities.load("tester/resources/dand-mask.gif");
		img1 = Utilities.applyAlphaMask(img1, msk);
		
		temp = new BufferedImage(msk.getWidth(), msk.getHeight(), BufferedImage.TYPE_INT_ARGB);
		temp.createGraphics().drawImage(msk,0,0,null);
		msk = temp;

	}



	@Override
	public void render(Graphics2D g) {
		fillWithColor(BLACK);
		g.drawImage(img1, 100,100, null);
		
		Composite old = g.getComposite();
		g.setComposite(BlendComposite.ColorDodge);
		g.drawImage(msk, 100,100, null);
		g.setComposite(old);
		
	}

	@Override
	public void update() {}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MaskTester("Mask Tester",640,480).start();

	}

}
