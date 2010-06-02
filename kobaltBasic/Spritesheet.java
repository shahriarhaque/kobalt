package kobaltBasic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class represents a sprite sheet and contains convenience methods for extracting images from it.
 * @author Shahriar Haque
 */
public final class Spritesheet {
	
	/** Given a string, this map returns the corresponding image from the tileset. **/
	public final HashMap<String, BufferedImage> map = new HashMap<String, BufferedImage>();
	
	public int imgWidth = 0;
	public int imgHeight = 0;
	public int numColumns = 0;
	public int numRows = 0;
	
	/** Offset between each row of images in the sprite sheet **/
	public int hOffset = 0;
	
	/** Offset between each column of images in the sprite sheet **/
	public int vOffset = 0;
	
	public int x;
	public int y;
	
	/** Width of the entire sprite sheet **/
	public int width;
	
	/** Height of the entire sprite sheet **/
	public int height;
	
	/** Mask color used by this sprite sheet. This color will be receive an alpha value of 0.0 **/
	public Color maskColor = null;
	
	/** The BufferedImage that holds the sprite sheet after loading it **/
	public BufferedImage set;
	
	public Spritesheet(String fileLocation){
		set = Utilities.load(fileLocation);
	}
	
	/**
	 * Renders the whole sprite sheet as an image on the screen
	 * @param g
	 * @param grid If grid is true, a white grid is drawn on top of the sprite sheet for debugging.
	 */
	public void render(Graphics2D g, boolean grid){
		g.drawImage(set,x,y,null);
		g.setColor(Color.white);
		
		
		for(int i = x; i < (x+width); i+= (imgWidth+hOffset) ){
			g.drawLine(i, y, i, (y+height));
			g.drawLine(i+imgWidth, y, i+imgWidth, (y+height));
		}
		
		for(int i = y; i < (y+height); i+=(imgHeight+vOffset) ){
			g.drawLine(x, i, (x+width), i);
			g.drawLine(x,i+imgHeight, (x+width),i+imgHeight);
		}
	}
	
	
	/**
	 * Returns an array consisting of a vertical row of images from a tileset.
	 * @param startX Starting X coordinate of the row.
	 * @param startY Starting Y coordinate of the row.
	 * @param w Width of each image.
	 * @param h Height of each image.
	 * @param offset The distance between each of image.
	 * @param numImages Number of images in the row.
	 * @return
	 */
	public BufferedImage[] extractRow(int startX, int startY, int w, int h, int offset, int numImages){
		
		BufferedImage[] array = new BufferedImage[numImages];

		for(int i = 0; i < numImages; i++){
			array[i] = Utilities.removeMask(set.getSubimage(startX + (i*(w + offset) ), startY, w, h), maskColor );
		}
		
		return array;
	}
	

	/**
	 * Reads an entire tile and labels them with the labels found in the label file.
	 * @param imgLabelFile The file that contains the labels for the tile set.
	 * @return
	 */
	public void extractAll(String imgLabelFile){
	

		try {
			Scanner in = new Scanner(new File(imgLabelFile));
			BufferedImage img;
			String label;
			
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < numColumns; j++){
					img = Utilities.removeMask(set.getSubimage( j*(imgWidth + hOffset), i*(imgHeight + vOffset), imgWidth, imgHeight), maskColor);
					label = in.next();
					map.put(label, img);
				}
			
			}


		} catch (FileNotFoundException e) {
			System.out.println("Image Label File: " + imgLabelFile + " not found");
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
