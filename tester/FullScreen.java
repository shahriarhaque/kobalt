package tester;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

public class FullScreen {

	private static int counter = 0;

	private static final int MAX = 50;

	private static DisplayMode MODES[] = new DisplayMode[] {
		new DisplayMode(640, 480, 32, 0), new DisplayMode(640, 480, 16, 0),
		new DisplayMode(640, 480, 8, 0) };

	private static DisplayMode getBestDisplayMode(GraphicsDevice device) {
		for (int x = 0, xn = MODES.length; x < xn; x++) {
			DisplayMode[] modes = device.getDisplayModes();
			for (int i = 0, in = modes.length; i < in; i++) {
				if (modes[i].getWidth() == MODES[x].getWidth()
						&& modes[i].getHeight() == MODES[x].getHeight()
						&& modes[i].getBitDepth() == MODES[x].getBitDepth()) {
					return MODES[x];
				}
			}
		}
		return null;
	}

	public static void main(String args[]) {
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
		.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment
		.getDefaultScreenDevice();
		DisplayMode originalDisplayMode = graphicsDevice.getDisplayMode();

		try {
			Frame frame = new Frame();
			frame.setUndecorated(true);
			frame.setIgnoreRepaint(true);
			graphicsDevice.setFullScreenWindow(frame);
			if (graphicsDevice.isDisplayChangeSupported()) {
				graphicsDevice
				.setDisplayMode(getBestDisplayMode(graphicsDevice));
			}
			frame.createBufferStrategy(2); // 2 buffers
			Rectangle bounds = frame.getBounds();
			BufferStrategy bufferStrategy = frame.getBufferStrategy();
			while (!done()) {
				Graphics g = null;
				try {
					g = bufferStrategy.getDrawGraphics();
					if ((counter <= 2)) { // 2 buffers
						g.setColor(Color.CYAN);
						g.fillRect(0, 0, bounds.width, bounds.height);
					}
					g.setColor(Color.RED);
					// redraw prior line, too, since 2 buffers
					if (counter != 1) {
						g.drawLine(counter - 1, (counter - 1) * 5,
								bounds.width, bounds.height);
					}
					g.drawLine(counter, counter * 5, bounds.width,
							bounds.height);
					bufferStrategy.show();
				} finally {
					if (g != null) {
						g.dispose();
					}
				}
				try {
					Thread.sleep(250);
				} catch (InterruptedException ignored) {
				}
			}
		} finally {
			graphicsDevice.setDisplayMode(originalDisplayMode);
			graphicsDevice.setFullScreenWindow(null);
		}
		System.exit(0);
	}

	private static boolean done() {
		return (counter++ == MAX);
	}
}
