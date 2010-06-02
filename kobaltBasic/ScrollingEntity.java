package kobaltBasic;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * ScrollingEntities are used to represent backgrounds that wrap around as the player moves in the world. They are
 * typical in old-school platform games to simulate the parallax effect. To use this class, one has to provide it with
 * coordinates and the dimensions of the projection panel. The projection panel is the area of the screen on which
 * the image will be scrolled. Setting the {@code x_velocity} and {@code y_velocity} simulates movement and wrap-around
 * behavior.
 * @author Shahriar Haque
 *
 */
public class ScrollingEntity extends SpriteActor {

	
	/** Height and width of the panel on to which the scrolling image will be projected on. **/
	public int panelWidth;
	public int panelHeight;
	
	/** Coordinates of the panel on to which the scrolling image will be projected on.**/
	public int panelX;
	public int panelY;
	
	// used simulate 360 degree scrolling, each rectangle has the exact dimensions of the scrolling image.
	private Rectangle2D.Double[] guide = new Rectangle2D.Double[9];
	
	// rectangle with the same dimensions as the projection panel
	private Rectangle2D.Double panel;
	
	
	/**
	 * Creates a new ScrollingEntity that has the ability to scroll in all possible directions.
	 * @param s The sprite that represents the image.
	 * @param pX X of the projection panel.
	 * @param pY Y of the projection panel.
	 * @param pWidth Width of the projection panel.
	 * @param pHeight Height of the projection panel.
	 */
	public ScrollingEntity(Sprite s, int pX, int pY, int pWidth, int pHeight) {
		super(s, pX, pY);
		
		panelX = pX;
		panelY = pY;
		panelWidth = pWidth;
		panelHeight = pHeight;
		
		// initializing according to the dimensions of the projection panel
		panel = new Rectangle2D.Double(pX, pY, pWidth, pHeight);
		
		// guide[0] is aligned with the upper left corner of the projection panel
		guide[0] = new Rectangle2D.Double(panelX, panelY, width, height);
		
		guide[1] = new Rectangle2D.Double(panelX-width, panelY-height, width, height);
		guide[2] = new Rectangle2D.Double(panelX, panelY-height, width, height);
		guide[3] = new Rectangle2D.Double(panelX+width,panelY-height, width, height);
		
		guide[4] = new Rectangle2D.Double(panelX-width, panelY, width, height);
		guide[5] = new Rectangle2D.Double(panelX+width, panelY, width, height);
		
		guide[6] = new Rectangle2D.Double(panelX-width, panelY+height, width, height);
		guide[7] = new Rectangle2D.Double(panelX, panelY+height, width, height);
		guide[8] = new Rectangle2D.Double(panelX+width,panelY+height, width, height);

		
	}
	
	/*
	 * Updates the position of the guiding rectangles with respect to guide[0]. Guide[0] always
	 * assumes the coordinates of the ScrollingEntity itself.
	 */
	private void updateGuides(){
		double gx = x;
		double gy = y;
		
		
		guide[0].x = gx;
		guide[0].y = gy;
		
		guide[1].x = gx-width;
		guide[1].y = gy-height;
		
		guide[2].x = gx;
		guide[2].y = gy-height;
		
		guide[3].x = gx+width;
		guide[3].y = gy-height;
		
		guide[4].x = gx-width;
		guide[4].y = gy;
		
		guide[5].x = gx+width;
		guide[5].y = gy;
		
		guide[6].x = gx-width;
		guide[6].y = gy+height;
		
		guide[7].x = gx;
		guide[7].y = gy+height;
		
		guide[8].x = gx+width;
		guide[8].y = gy+height;
		
		
		
	
	}
	
	public void update(){
		float fraction = (BasicGame.interval/1000.0f);
		
		x += (x_velocity * fraction);
		y += (y_velocity * fraction);

		x = x%width;
		y = y%height;

		
		updateGuides();

	}
	
	
	public void render(Graphics2D g){
		BufferedImage img = theSprite.getCurrentFrame();
		
		int sx;
		int sy;
		
		int sw;
		int sh;
		
		// if the panel is fully contained within any of the guiding rectangles, crop and display the cropped image
		for(int i = 0; i < 9; i++){
			if(guide[i].contains(panel)){
				sx = (int) (panel.x -  guide[i].x);
				sy = (int) (panel.y - guide[i].y);
				g.drawImage(img, 0, 0, panelX+panelWidth, panelY+panelHeight, sx, sy, sx+panelWidth, sy+panelHeight,null );

				return;
			}
		}
		
		// if the panel intersects several guiding rectangles, crop the intersections, stich them and display
		for(int i = 0; i < 9; i++){
			if(panel.intersects(guide[i])){
				Rectangle2D r = panel.createIntersection(guide[i]);
				
				sx = (int) (r.getX() - guide[i].x);
				sy = (int) (r.getY() - guide[i].y);
				sw = (int)r.getWidth();
				sh = (int)r.getHeight();

				
				g.drawImage(img, (int)r.getX(), (int)r.getY(), (int)(r.getX() + r.getWidth()), 
						(int) (r.getY() + r.getHeight()), sx,sy, sx+sw, sy+sh, null);
				
			}
		}
		

	}
	

	

}
