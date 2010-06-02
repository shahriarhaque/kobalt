package kobaltBasic;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import experimental.network.NetworkService;


/**
 * This provides all the necessary GUI and graphics infrastructure on which a 2D game can run
 * on. The ControlledByKeyboard and ControlledByMouse interfaces provide keyboard and mouse playing
 * functionality to the game. In order to use the facilities of this class, extend it and call
 * the {@link #start()} method to begin the game.
 * @author Shahriar Haque
 * 
 *
 */
public abstract class BasicGame extends GameScreen {

	/** Width of the game window **/
	private final int screenWidth;

	/** Height of the game window **/
	private final int screenHeight;

	private final String gameTitle;

	/** Interval between now and the last time a time sample was taken **/
	public static long interval = 0;

	/** 
	 * This is the time interval (in milliseconds) between each rendering loop of the game.
	 * Lower this only if your game is appearing to run slowly.
	 */
	public int REFRESH_INTERVAL = 10;

	/** The back buffer onto which all game graphics are drawn. **/
	public static BufferedImage backBuffer; 

	private final Graphics2D bufferGraphics;

	private final GameWindow gWin;

	public static final Color WHITE = Color.white;
	public static final Color BLACK = Color.black;

	/** Arraylist that holds various game screens (e.g. Intro menu, pause menu, inventory etc.) **/
	public static final ArrayList<GameScreen> screenList = new ArrayList<GameScreen>();

	/** The index of the current screen within the screen list array **/
	public static int currentScreen = 0;

	public NetworkService netService;

	public boolean fullScreen = false;
	private DisplayMode displayMode;

	// end of variable declaration

	/**
	 * Constructor that initializes the GUI window in which the game runs.
	 * @param title The title of the game GUI window.
	 * @param w Width of the window in pixels.
	 * @param h Height of the window in pixels.
	 */
	public BasicGame(String title, int w, int h){
		gameTitle = title;
		screenWidth = w;
		screenHeight = h;

		backBuffer = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_RGB);
		bufferGraphics = (Graphics2D) backBuffer.getGraphics();

		gWin = new GameWindow(gameTitle,screenWidth,screenHeight);

		screenList.add(this); // the main game screen itself is one of the screens

	}

	public BasicGame(String title, int w, int h, boolean fullScreen){
		this(title,w,h);
		if(!fullScreen) return;

		displayMode = new DisplayMode(w,h,32,0); 
	}

	/**
	 * Draws the given text on the screen with the given Color
	 * @param text The string to be drawn on the screen.
	 * @param x X coordinate.
	 * @param y Y coordiante.
	 * @param c Color of the text.
	 */
	public void drawText(String text, int x, int y, Color c){
		bufferGraphics.setColor(c);
		bufferGraphics.drawString(text, x, y);
	}

	/**
	 * Flood fills the screen with the given color.
	 * @param c Flood-fill color.
	 */
	public void fillWithColor(Color c){
		bufferGraphics.setColor(c);
		bufferGraphics.fillRect(0, 0, screenWidth, screenHeight);
	}

	/**
	 * Draws any arbitrary geometrical object that implements the {@link Shape} interface.
	 * @param s The shape to be drawn.
	 * @param c Color of the shape's outline.
	 */
	public void drawShape(Shape s, Color c){
		bufferGraphics.setColor(c);
		bufferGraphics.draw(s);
	}

	public void addNetworkService(NetworkService net){
		netService = net;
	}


	/**
	 * Starts the main game loop and runs it continuously. Frame rate of the is controlled through 
	 * the game is controlled via the {@link #REFRESH_INTERVAL} field.
	 */
	public void start(){

		long startTime = System.currentTimeMillis();

		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		DisplayMode originalDisplayMode = graphicsDevice.getDisplayMode();


		gWin.createBufferStrategy(2);
		BufferStrategy bufferStrategy = gWin.getBufferStrategy();


		if(fullScreen){
			gWin.setUndecorated(true);
			gWin.setIgnoreRepaint(true);
			graphicsDevice.setFullScreenWindow(gWin);
			if (graphicsDevice.isDisplayChangeSupported()) graphicsDevice.setDisplayMode(displayMode);
			else{
				System.out.println("Display Mode could not be changed");
			}
		}


		while(true){
			interval = System.currentTimeMillis() - startTime;

			screenList.get(currentScreen).update();
			startTime = System.currentTimeMillis();

			screenList.get(currentScreen).render(bufferGraphics); // render of backbuffer

			gWin.screenGFX = (Graphics2D) bufferStrategy.getDrawGraphics();
			gWin.screenGFX.drawImage(backBuffer,0,0,null);

			bufferStrategy.show();


			if(gWin.screenGFX != null){
				gWin.screenGFX.dispose();
			}

			if(!gWin.isShowing()){
				gWin.dispose();
				break;
			}

			try { Thread.sleep(REFRESH_INTERVAL); } catch(Exception e) {}
		}


		if(fullScreen){
			graphicsDevice.setDisplayMode(originalDisplayMode);
			graphicsDevice.setFullScreenWindow(null);
		}


	}


	private class GameWindow extends JFrame implements KeyListener, MouseListener,
	MouseMotionListener, WindowListener{

		private static final long serialVersionUID = 1L;
		Graphics2D screenGFX;

		public GameWindow(String title, int w, int h){
			this.setTitle(title);
			this.setSize(w, h);
//			this.setResizable(false);
			this.addKeyListener(this);
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.addWindowListener(this);
			this.setIgnoreRepaint(true);
			this.setVisible(true);
			this.setIgnoreRepaint(true);

		}


		public void keyPressed(KeyEvent k) { screenList.get(currentScreen).listenToKeyPress(k); }

		public void keyReleased(KeyEvent k) { screenList.get(currentScreen).listenToKeyRelease(k); }

		public void mouseClicked(MouseEvent m) { screenList.get(currentScreen).listenToMouseClick(m); }

		public void mousePressed(MouseEvent m) { screenList.get(currentScreen).listenToMousePress(m); }

		public void mouseReleased(MouseEvent m) { screenList.get(currentScreen).listenToMouseRelease(m); }

		public void mouseMoved(MouseEvent m) { screenList.get(currentScreen).listenToMouseMove(m); }

		public void mouseDragged(MouseEvent m) {}
		public void keyTyped(KeyEvent k) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}

		public void windowActivated(WindowEvent arg0) {}

		public void windowClosed(WindowEvent e) {}

		public void windowClosing(WindowEvent e) {
			if(netService != null) netService.closeSession();
			handleWindowClosing(e);
		}

		public void windowDeactivated(WindowEvent e) {}

		public void windowDeiconified(WindowEvent e) {}

		public void windowIconified(WindowEvent e) {}

		public void windowOpened(WindowEvent e) {}

	} // end of private class GameWindow



}
