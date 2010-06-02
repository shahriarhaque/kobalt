package kobaltBasic;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;

public class SpriteActor extends BasicActor {
	
	public int width,height; 
	public final Sprite theSprite;
	

	
	
	public SpriteActor(Sprite s, int x, int y){
		super(x,y);
		
		theSprite = s;
		width = s.getCurrentFrame().getWidth();
		height= s.getCurrentFrame().getHeight();
		setBounds(new Area(new Rectangle(x, y, width, height)));
		
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
		g.drawImage(theSprite.getCurrentFrame(),(int)x,(int)y,null);
	}
	

}
