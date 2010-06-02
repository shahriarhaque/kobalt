package effects;

import java.awt.Graphics2D;

/**
 * All objects that needs to be rendered on a screen, but is not represented by a Sprite should
 * implement this interface. Special effects operations can be performed on implementing classes.
 * @author Shahriar Haque
 *
 */
public interface Drawable {
	

	/**
	 * The custom drawing code for this on-screen object should be implemented here.
	 * @param g The graphics context of the screen on which the object will be drawn on.
	 */
	public void draw(Graphics2D g);
	
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	

}
