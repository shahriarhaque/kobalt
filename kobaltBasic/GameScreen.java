package kobaltBasic;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public abstract class GameScreen {

	private final ArrayList<KeyboardSupport> kbControls = new ArrayList<KeyboardSupport>();
	private final ArrayList<MouseSupport> msControls = new ArrayList<MouseSupport>();
	private final ArrayList<WindowListener> winListeners = new ArrayList<WindowListener>();

	/**
	 * Adds the functionality of being able to be controlled by the keyboard to the passed object.
	 * @param cbk The game element that is to be controlled by keyboard.
	 */
	public void addKeyboardSupport(KeyboardSupport cbk){ kbControls.add(cbk); }

	/**
	 * Adds the functionality of being able to be controlled by the mouse to the passed object.
	 * @param cbm The game element that is to be controlled by mouse.
	 */
	public void addMouseSupport(MouseSupport cbm){ msControls.add(cbm); }

	
	public void addWindowListener(WindowListener winListener) { winListeners.add(winListener); }



	// public final helper methods

	public final void listenToKeyPress(KeyEvent k){
		for(KeyboardSupport cbk: kbControls) cbk.handleKeyPress(k.getKeyCode());
	}

	public final void listenToKeyRelease(KeyEvent k){
		for(KeyboardSupport cbk: kbControls) cbk.handleKeyRelease(k.getKeyCode());
	}

	public final void listenToMousePress(MouseEvent m){
		for(MouseSupport cbm: msControls) cbm.handleMousePress(m.getX(),m.getY());
	}

	public final void listenToMouseRelease(MouseEvent m){
		for(MouseSupport cbm: msControls) cbm.handleMouseRelease(m.getX(),m.getY());
	}

	public final void listenToMouseClick(MouseEvent m){
		for(MouseSupport cbm: msControls) cbm.handleMouseClick(m.getX(),m.getY());
	}

	public final void listenToMouseMove(MouseEvent m){
		for(MouseSupport cbm: msControls) cbm.handleMouseMove(m.getX(),m.getY());
	}
	
	public final void handleWindowClosing(WindowEvent e){
		for(WindowListener win: winListeners) win.windowClosing(e);
	}


	/**
	 * Renders all the game elements. Simply call the render(Graphics2D) method of your game
	 * elements if you do not wish to customize the rendering process.
	 * @param g The graphics context on to which the game elements are to be rendered.
	 */
	public abstract void render(Graphics2D g);

	/**
	 * Used to update the logic for all the game elements. This method is called during each
	 * iteration of the game loop.
	 */
	public abstract void update();

}
