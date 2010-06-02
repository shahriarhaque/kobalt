package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kobaltBasic.BasicGame;
import com.jhlabs.image.WaterFilter;

public class WaterRippleEffect {
	
	public int center_x;
	public int center_y;
	public float amplitude = 10;
	public float wavelength = 16;
	public int duration = 10000;
	public float radius = 50;
	
	private int elapsed = 0;
	private float phase = 0;
	public float speed = 15;
	
	public boolean done = false;
	
	private WaterFilter effect;
	
	public WaterRippleEffect(int x, int y){
		this.center_x = x;
		this.center_y = y;
		
		effect = new WaterFilter();

	}
	
	public void reset(){
		elapsed = 0;
		done = false;
	}
	
	public void update(){
		
		effect.setAmplitude(amplitude);
		effect.setWavelength(wavelength);
		effect.setRadius(radius);
		
		if(elapsed > duration) done = true;
		
		float fraction = BasicGame.interval/1000.0f;
		phase += speed*fraction;
		effect.setPhase(phase);
		
		
		elapsed += BasicGame.interval;
		
		
		
	}
	
	public void applyEffect(Graphics2D g){
		if(done) return;
		
		BufferedImage b = BasicGame.backBuffer.getSubimage(center_x-(int)radius, center_y-(int)radius, (int)(2*radius), (int)(2*radius));
		effect.filter(b, b);
		
		g.drawImage(b,center_x-(int)radius, center_y-(int)radius,null);
		
	}

}
