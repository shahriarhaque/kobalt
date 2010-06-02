package kobaltBasic;

/**
 * Provides the necessary methods to allow a game-element to be mouse-controlled.
 * @author Shahriar Haque
 *
 */
public interface MouseSupport {
	
	/**
	 * Handles the event of a mouse click on the given location.
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 */
	public abstract void handleMouseClick(int x, int y);
	
	/**
	 * Handles the event of a mouse button being pressed down at the given location.
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 */
	public abstract void handleMousePress(int x, int y);
	
	/**
	 * Handles the event of a mouse button being released from the given location.
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 */
	public abstract void handleMouseRelease(int x, int y);
	
	/**
	 * Handles the event of the mouse pointer being moved to another location.
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 */
	public abstract void handleMouseMove(int x, int y);

}
