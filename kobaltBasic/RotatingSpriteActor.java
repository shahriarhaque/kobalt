package kobaltBasic;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class RotatingSpriteActor extends RotatingActor {

	
	public int width,height; 
	public final Sprite theSprite;
	
	public RotatingSpriteActor(Sprite s, double x, double y) {
		super(x, y);
		
		theSprite = s;
		width = s.getCurrentFrame().getWidth();
		height= s.getCurrentFrame().getHeight();
		anchor_x = width/2;
		anchor_y = height/2;
		
		setBounds(new Area(new Rectangle((int)x,(int)y,width,height)));
		
	}
	
	public void update(){
		super.update();
		
		if(lastUpdated >= UPDATE_INTERVAL){
			this.theSprite.advanceFrame();
			lastUpdated = 0;
		}

		else lastUpdated += BasicGame.interval;
	}
	
	public void render(Graphics2D g){
		
		BufferedImage srcImage = theSprite.getCurrentFrame();
		
		AffineTransform oldTransform = g.getTransform();

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setPaint(new TexturePaint(srcImage,
				new Rectangle2D.Float(0, 0, srcImage.getWidth(), srcImage.getHeight())));

		AffineTransform transform = new AffineTransform();
		
		transform.translate(x, y);
		
		double theta = Math.toRadians(heading);
		transform.rotate(theta, anchor_x, anchor_y);
		g.transform(transform);
		g.fillRect(0, 0, srcImage.getWidth(), srcImage.getHeight());
		
		g.setTransform(oldTransform);		

	}

}
