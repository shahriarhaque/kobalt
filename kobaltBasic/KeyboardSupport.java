package kobaltBasic;

/**
 * Provides the necessary methods to allow keyboard controlling features to your game and/or
 * GameEntity objects.
 * @author Shahriar Haque
 *
 */
public interface KeyboardSupport {
	
	/**
	 * Handles the event of a key being pressed.
	 * @param keyCode The integer representation of the ascii value of the pressed key.
	 */
	public abstract void handleKeyPress(int keyCode);
	
	/**
	 * Handles the event of a key being released.
	 * @param keyCode The integer representation of the ascii value of the pressed key.
	 */
	public abstract void handleKeyRelease(int keyCode);

}
