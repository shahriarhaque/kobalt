package particle;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

/**
 * A circular particle that starts off fully opaque and gradually becomes transparent as it ages.
 * @author Shahriar Haque
 *
 */
public class FadingParticle extends Particle {
	
	public void render(Graphics2D g){
		
		Composite oldComposite = g.getComposite();
		
		float a = (float)age;
		float span = (float) lifeSpan;
		
		
		
		Composite fade;
		fade = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - (a/span)  );
		
		g.setComposite(fade);
		g.fillOval((int)x, (int)y, size, size);
		g.setComposite(oldComposite);
	}

}
