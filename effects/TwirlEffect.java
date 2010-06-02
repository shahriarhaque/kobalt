package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kobaltBasic.BasicGame;
import com.jhlabs.image.TwirlFilter;

public class TwirlEffect {
	
	public int center_x;
	public int center_y;

	public int duration = 10000;
	public float radius = 50;
	
	private int elapsed = 0;
	private float angle = 0;
	
	/** Rotational velocity in degrees/second. **/
	public float speed = 120;
	
	public boolean done = false;
	
	private TwirlFilter effect;
	
	public TwirlEffect(int x, int y){
		this.center_x = x;
		this.center_y = y;
		
		effect = new TwirlFilter();

	}
	
	public void reset(){
		elapsed = 0;
		done = false;
	}
	
	public void update(){
		

		effect.setRadius(radius);
		
		
		if(elapsed > duration) done = true;
		
		float fraction = BasicGame.interval/1000.0f;
		angle += speed*fraction;
		effect.setAngle((float)Math.toRadians(angle));
		
		
		elapsed += BasicGame.interval;
		
		
		
	}
	
	public void applyEffect(Graphics2D g){
		if(done) return;
		
		BufferedImage b = BasicGame.backBuffer.getSubimage(center_x-(int)radius, center_y-(int)radius, (int)(2*radius), (int)(2*radius));
		effect.filter(b, b);
		
		g.drawImage(b,center_x-(int)radius, center_y-(int)radius,null);
		
	}

}
