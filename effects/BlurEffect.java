package effects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import kobaltBasic.BasicGame;
import kobaltBasic.SpriteActor;

/**
 * A special effects class that applies a blur effect to the current frame of a given GameEntity.
 * The effect uses a default (3x3) kernel that the user can access staticly. If there is a need
 * to change the kernel, the value of {@code kernelDim} also has to be changed accordingly.
 * @author Shahriar Haque
 *
 */
public class BlurEffect {
	
	
	/** Kernel of the blur filter that will be applied to the image **/
	public static float[] kernel = new float[]{
		0.1f, 0.1f, 0.1f,
		0.1f, 0.2f, 0.1f,
		0.1f, 0.1f, 0.1f
	};
	
	/** Dimension of the kernel, kernel are always square matrices. Thus this
	 *  is the dimension for both rows and columns. Default value is 3. **/
	public static int kernelDim = 3;
	
	
	public static void applyEffect(Graphics2D g, BufferedImage b, int x, int y){
		
		// get the background on which the frame will be drawn
		BufferedImage bg = BasicGame.backBuffer.getSubimage(x, y, b.getWidth(), b.getHeight());
		bg.getGraphics().drawImage(b,0,0,null);

		// apply the blur filter
		ConvolveOp op = new ConvolveOp(new Kernel(kernelDim,kernelDim, kernel));
		Image img = op.filter(bg, null);
		g.drawImage(img,x, y, null);
	}
	
	public static void applyEffect(Graphics2D g, SpriteActor entity){
		applyEffect(g, entity.theSprite.getCurrentFrame(), (int)entity.x, (int)entity.y);	
	}
	
	
	public static void applyEffect(Graphics2D g, Drawable d){
		
		// get the background on which the frame will be drawn
		BufferedImage bg = BasicGame.backBuffer.getSubimage(d.getX(), d.getY(), d.getWidth(), d.getHeight());
		d.draw((Graphics2D) bg.getGraphics());

		// apply the blur filter
		ConvolveOp op = new ConvolveOp(new Kernel(kernelDim,kernelDim, kernel));
		Image img = op.filter(bg, null);
		g.drawImage(img,d.getX(), d.getY(), null);
	}
	


}
