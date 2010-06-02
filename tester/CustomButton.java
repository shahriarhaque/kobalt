package tester;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import kobaltBasic.MouseSupport;
import effects.Drawable;
import effects.FadeEffect;


public class CustomButton implements Drawable,MouseSupport {

	
	String label;
	private boolean mouseOver = false;
	@SuppressWarnings("unused")
	private boolean pressed = false; 
	
	private final Rectangle bounds = new Rectangle(0,0,10,10);
	
	public Color fillColor = new Color(36,36,36);
	FadeEffect f = new FadeEffect(FadeEffect.FADE_IN);
	
	
	public int x,y,w,h;
	
	

	public CustomButton(String label, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.label = label;
		bounds.x = x;
		bounds.y = y;
		bounds.width = w;
		bounds.height = h;
		
		f.duration = 1500;
		
		
	}

	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if(! f.done){
			g.setColor(fillColor);
			f.applyEffect(g, this); // one time effect
		}
		
		else{
			if(!mouseOver)g.setColor(fillColor);
			else g.setColor(new Color(100,100,100));
			draw(g);

			
		}
		
		
		

		
	}
	
	public void draw(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Composite oldComposite = g.getComposite();
		Composite fade = null;
		fade =  AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f  );
		
		g.setComposite(fade);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.setComposite(oldComposite);
		
		g.setFont(new Font("Arial",0,36));
		g.drawString("Start", x+36, y+40);
	}

	public void handleMouseClick(int x, int y) {}

	public void handleMouseMove(int x, int y) {
		if(bounds.contains(x, y)) mouseOver = true;
		else {
			mouseOver = false;
		}
		
	}

	public void handleMousePress(int x, int y) {
		if(bounds.contains(x,y)){
			pressed = true;
			PongIntro.fadeOut = true;
		}
		
	}

	public void handleMouseRelease(int x, int y) {
		if(bounds.contains(x,y)){
			pressed = false;
		}
		
	}

	public int getHeight() {
		
		return h;
	}

	public int getWidth() {
		
		return w;
	}

	public int getX() {
		
		return x;
	}

	public int getY() {
		
		return y;
	}


}
