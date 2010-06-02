package particle;

import java.awt.Composite;
import java.awt.Graphics2D;

import effects.BlendComposite;

/**
 * A circular particle that uses additive blending while rendering itself.
 * @author Shahriar Haque
 */
public class MagicParticle extends Particle {
	
	public void render(Graphics2D g){
		
		Composite oldComposite = g.getComposite();
	
		
		Composite fade;
		fade = BlendComposite.getInstance(BlendComposite.BlendingMode.ADD);
		
		g.setComposite(fade);
		g.fillOval((int)x, (int)y, size, size);
		g.setComposite(oldComposite);
	}

}
