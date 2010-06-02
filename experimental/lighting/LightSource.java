package experimental.lighting;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class LightSource {
	public double x;
	public double y;
	
	/** The effective range of the light source**/
	public double range;
	
	/** The image used to render the light source **/
	BufferedImage img = null;
	
	public LightSource(BufferedImage image){
		this.img = image;
	}
	
	

	/** If an image is present for this LightSource, renders it at (x,y) **/
	public void render(Graphics2D g){
		if(img != null)g.drawImage( img, (int)x, (int)y,  null  );
	}
	
	/** Returns the distance from itself to the given coordinate **/
	public final double getDistance(int x1, int y1){
		return Math.sqrt( ((x1-x)*(x1-x)) + ((y1-y)*(y1-y))  );
	}

}
