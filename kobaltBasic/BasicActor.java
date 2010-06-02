package kobaltBasic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;


/**
 * The most basic of game-play elements. This class should act as a base for all playable,
 * non-playable, and interactive game elements. A GameEntity object can hold a sprite (single /
 * multiple frames), along with coordinates and dimension. It also handles it own rendering.
 * Any sub-class can initialize it with a sprite and change the {@code x_velocity} and
 * {@code y_velocity} variables to be able to the entity. Use the {@link KeyboardSupport}
 * and/or {@link MouseSupport} interfaces for implementing playable characters.
 * 
 * @author Shahriar Haque
 * Last Modified: 18 December, 2008
 *
 */
public abstract class BasicActor {

	//changed accessor to public on June 13, 2008
	public double x,y; // changed type to double on December 18, 2008


	/** 2d component of the velocity vector **/
	public double x_velocity = 0, y_velocity = 0;

	public static final int RIGHT = KeyEvent.VK_RIGHT;
	public static final int LEFT = KeyEvent.VK_LEFT;
	public static final int UP = KeyEvent.VK_UP;
	public static final int DOWN = KeyEvent.VK_DOWN;

	public int UPDATE_INTERVAL = 40;
	public int lastUpdated = 0;
	
	
	Area vectorBound = null;
	
	/** If true, the outline of the bound will be overlayed on the sprite **/
	public boolean drawVector = false;
	

	/**
	 * Initializes a new GameEntity and places it at the given location.
	 * @param posX X Position
	 * @param posY Y Position
	 */
	public BasicActor(double x, double y){
		this.x = x;
		this.y = y;
	}
	


	/**
	 * Updates the (x,y) coordinates by adding the {@code x_velocity} and {@code y_velocity}
	 * parameters.
	 *
	 */
	public void update(){

		float fraction = (BasicGame.interval/1000.0f);
		
		x += (x_velocity * fraction)  ;
		y += (y_velocity * fraction) ; 
	}

	/**
	 * Renders the GameEntity on the given graphics context{@code (g)}. Rendering is performed at
	 * the (x,y) location of the instance.
	 * @param g The graphics context on to which the GameEntity is to be drawn.
	 */
	public abstract void render(Graphics2D g);


	public void renderBounds(Graphics2D g){
		g.setColor(Color.white);
		g.draw(getBounds());
	}
	
	public void setBounds(Area a){ vectorBound = a; }
	
	/**
	 * Returns a new instance of the bounding Area with the latest translations applied.
	 * @return
	 */
	public Area getBounds(){
		AffineTransform transform = new AffineTransform();
		transform.translate(x, y);

		return vectorBound.createTransformedArea(transform);
		
	}
	
	/**
	 * Returns true if the current VectorGameEntity's bound intersects the bound of the other.
	 * @param vge The other VectorGameEntity
	 * @return
	 */
	public boolean collides(BasicActor actor){
		Area a1 = getBounds();
		Area a2 = actor.getBounds();
		
		a1.intersect(a2);
		
		return !a1.isEmpty(); // if two shapes collide, their intersection is not empty
	}

	

}
