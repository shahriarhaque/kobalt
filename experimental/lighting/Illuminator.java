package experimental.lighting;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import kobaltBasic.SpriteActor;

/**
 * This class provides support for rendering lighting effects. In this experimental version only
 * one light source and one object can be illuminated. The rendering only supports diffuse effects.
 * Whenever the target object is out of the range of a light source, the default sprite is untouched.
 * Within range, the brightness of the target increases as it approaches the light source.
 * 
 * This is still an experimental implementation.
 * @author Shahriar Haque
 *
 */
public class Illuminator {

	/** Light source that illuminates the scene. **/
	public LightSource source;
	/** The target object that needs to be illumniated.**/
	public SpriteActor target;

	public void render(Graphics2D g){

		source.render(g);

		BufferedImage sprite = target.theSprite.getCurrentFrame();

		float dist = (float) source.getDistance((int)target.x, (int)target.y);
		float inc = (float) (1.0f - (dist/source.range));
		if(inc <= 0) inc = 0;

		float rFactor = 1.0f + inc;

		if(rFactor <= 1.0f){
			g.drawImage(sprite,(int)target.x, (int)target.y,null );
			return;
		}

		// this is not being used because transparency is not preserved
//		BufferedImage b = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_RGB);
//		b.createGraphics().drawImage(sprite,0,0,null);
//
//		RescaleOp op = new RescaleOp(rFactor, 0.0f, null);
//		b = op.filter(b, null);

		BufferedImage b = getBufferedImage(target,rFactor);


		g.drawImage(b,(int)target.x, (int)target.y,null );
	}

	/**
	 * Given a GameEntity and a brightness factor, returns a new brighter image of that factor.
	 * @param t The GameEntity whose brightness has to be increased.
	 * @param factor The factor of brightness. 1.0 = no change, 2.0 = 100 % increase
	 * @return
	 */
	private BufferedImage getBufferedImage(SpriteActor t, float factor) {
		BufferedImage sprite = t.theSprite.getCurrentFrame();

		BufferedImage b = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
		b.createGraphics().drawImage(sprite,0,0,null);

		WritableRaster raster = b.getRaster();
		int[] pixel = new int[4];
		int width = raster.getWidth();
		int height = raster.getHeight();

		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){


				raster.getPixel(i, j, pixel);

				// do not do anything for fully transparent pixels
				if(pixel[3] == 0) continue;

				
				// multiply each color band by the factor, change to 255 if it overflows
				double color;
				color = pixel[0]*factor;				
				if(color > 255) color = 255;
				pixel[0] = (int) color;

				color = pixel[1]*factor;
				if(color > 255) color = 255;
				pixel[1] = (int) color;

				color = pixel[2]*factor;
				if(color > 255) color = 255;
				pixel[2] = (int) color;



				raster.setPixel(i, j, pixel);
			}
		}



		return b;
	}



}
