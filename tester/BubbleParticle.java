package tester;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kobaltBasic.Utilities;

import particle.Particle;

/**
 * A circular particle that starts off fully opaque and gradually becomes transparent as it ages.
 * @author Shahriar Haque
 *
 */
public class BubbleParticle extends Particle {
	
	
	static BufferedImage src = Utilities.load("tester/resources/bubble.gif");
	static BufferedImage msk = Utilities.load("tester/resources/bubble-mask.gif");
	static BufferedImage img = Utilities.applyAlphaMask(src, msk);
	
	
	public void render(Graphics2D g){
		
		Composite oldComposite = g.getComposite();
		
		float a = (float)age;
		float span = (float) lifeSpan;
		
		
		
		Composite fade;
		fade = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - (a/span)  );
		
		g.setComposite(fade);
		g.drawImage(img,(int)x, (int)y, null);
		g.setComposite(oldComposite);
	}

}
