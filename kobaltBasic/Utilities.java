package kobaltBasic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Simple utility class for loading images for sprites and also for applying transparency masks
 * (alpha masks) to existing images.
 * @author Shahriar Haque
 *
 */
public class Utilities {

	/**
	 * Loads and returns a {@link BufferedImage} object given the proper file location.
	 * @param fileLocation Relative path to the image.
	 * @return A<code> BufferedImage </code>object if load is successful, <code>null</null>
	 * otherwise.
	 */
	public static BufferedImage load(String fileLocation){

		try {
			return ImageIO.read(new File(fileLocation));
		} 


		catch (IOException e) {
			System.out.println(fileLocation + " not found");
			return null;
		}
	}

	/**
	 * Takes a souce image and and a gray-scale mask image of the same dimension, combines them
	 * to create an alpha-masked image, then returns the combined image. If the provided mask is
	 * not in gray-scale, gray values will be approximated from the red-channel of the pixels.
	 * The returned image will appear to be more transparent where the mask image was more white,
	 * and appear to be opaque where the mask was darker.
	 * @param src The source image.
	 * @param msk The gray-scale masked of the same dimensions of the src image.
	 * @return An alpha-masked {@link BufferedImage} with varying level of transparency.
	 * @throws IndexOutOfBoundsException if the supplied images are of different dimensions.
	 */
	public static BufferedImage applyAlphaMask(BufferedImage src, BufferedImage msk){

		BufferedImage alphaMask = new BufferedImage(msk.getWidth(),msk.getHeight(),BufferedImage.TYPE_INT_ARGB);
		alphaMask.createGraphics().drawImage(msk,0,0,null);

		BufferedImage dstOut = new BufferedImage(src.getWidth(),src.getHeight(),BufferedImage.TYPE_INT_ARGB);
		dstOut.createGraphics().drawImage(src,0,0,null);


		Raster maskRaster = alphaMask.getRaster();
		WritableRaster dstRaster = dstOut.getRaster();



		int w = maskRaster.getWidth();
		int h = maskRaster.getHeight();

		int[] mskPixel = new int[4]; // array of size 4 to hold [r,g,b,a] values
		int[] dstPixel = new int[4];

		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				maskRaster.getPixel(i, j, mskPixel);
				dstRaster.getPixel(i,j,dstPixel);

				dstPixel[3] = mskPixel[0]; // copies red value to alpha of the dst Image

				dstRaster.setPixel(i, j, dstPixel);

			}

		}


		return dstOut;
	}
	
	/**
	 * Given an image, makes the regions with the mask color transparent.
	 * @param src The source image of which the mask color has to be removed.
	 * @param maskColor Mask color
	 * @return A new BufferedImage with the regions of the mask color turned transparent.
	 */
	public static BufferedImage removeMask(BufferedImage src, Color maskColor){
		
		if(maskColor == null) return src;
		
		BufferedImage dstOut = new BufferedImage(src.getWidth(),src.getHeight(),BufferedImage.TYPE_INT_ARGB);
		dstOut.createGraphics().drawImage(src,0,0,null);
		
		int w = dstOut.getWidth();
		int h = dstOut.getHeight();
		int[] dstPixel = new int[4];
		
		WritableRaster dstRaster = dstOut.getRaster();
		
		int mRed = maskColor.getRed();
		int mGreen = maskColor.getGreen();
		int mBlue = maskColor.getBlue();
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				dstRaster.getPixel(i,j,dstPixel);
				
				if(dstPixel[0] == mRed  && dstPixel[1] == mGreen && dstPixel[2] == mBlue ){
					dstPixel[3] = 0;
					dstRaster.setPixel(i, j, dstPixel);
				}
				
			}
			
		}
		
		return dstOut;
		
	}
	
	/**
	 * Given an array of BufferedImages, returns a new array of images where each of the original images have
	 * been flipped horizontally.
	 * @param srcArray The source array of BufferedImages.
	 */
	public static BufferedImage[] flipHorizontal(BufferedImage[] srcArray){
		BufferedImage[] array = new BufferedImage[srcArray.length];
		
		for(int i = 0; i < array.length; i++) array[i] = flipHorizontal(srcArray[i]);
		
		return array;
	}
	
	/**
	 * Given a BufferedImage, returns a horizontally flipped version of it.
	 * @param src The source image
	 */
	public static BufferedImage flipHorizontal(BufferedImage src){
		BufferedImage temp = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		temp.createGraphics().drawImage(src, src.getWidth(), 0, 0, src.getHeight(), 
										0, 0, src.getWidth(), src.getHeight(), null);
		
		
		return temp;
	}
	
	/**
	 * Given an array of BufferedImages, returns a new array of images where each of the original images have
	 * been flipped vertically.
	 * @param srcArray The source array of BufferedImages.
	 */
	public static BufferedImage[] flipVertical(BufferedImage[] srcArray){
		BufferedImage[] array = new BufferedImage[srcArray.length];
		
		for(int i = 0; i < array.length; i++) array[i] = flipVertical(srcArray[i]);
		
		return array;
	}
	
	
	/**
	 * Given a BufferedImage, returns a vertically flipped version of it.
	 * @param src The source image
	 */
	public static BufferedImage flipVertical(BufferedImage src){
		BufferedImage temp = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		temp.createGraphics().drawImage(src, 0, src.getHeight(), src.getWidth(), 0,
	             						0, 0, src.getWidth(), src.getHeight(),null);

		return temp;
	}
	
	
	/**
	 * Returns an array containing the given number of colors by sampling them vertically
	 * over an equally divided distance.
	 * @param fileLocation The location of the image to be sampled.
	 * @param numColors The number of colors to sample.
	 * @return
	 */
	public static Color[] getVerticalColors(String fileLocation, int numColors){
		BufferedImage b = Utilities.load(fileLocation);
		return getVerticalColors(b,numColors);
	}
	
	/**
	 * Returns an array containing the given number of colors by sampling them horizontally
	 * over an equally divided distance.
	 * @param fileLocation The location of the image to be sampled.
	 * @param numColors The number of colors to sample.
	 * @return
	 */
	public static Color[] getHorizontalColors(String fileLocation, int numColors){
		BufferedImage b = Utilities.load(fileLocation);
		return getHorizontalColors(b,numColors);
	}
	
	/**
	 * Returns an array containing the given number of colors by sampling them vertically
	 * over an equally divided distance.
	 * @param b The BufferedImage to choose the colors from.
	 * @param numColors The number of colors to sample.
	 * @return
	 */
	public static Color[] getVerticalColors(BufferedImage b, int numColors){
		Color[] colors = new Color[numColors];
		
		
		int offset = b.getHeight()/numColors;
		
		for(int i = 0; i < numColors; i++){
			int color = b.getRGB(0, (i*offset));
			colors[i] = new Color(color);
		}
		
		return colors;
	}
	
	/**
	 * Returns an array containing the given number of colors by sampling them horizontally
	 * over an equally divided distance.
	 * @param b The BufferedImage to choose the colors from.
	 * @param numColors The number of colors to sample.
	 * @return
	 */
	public static Color[] getHorizontalColors(BufferedImage b, int numColors){
		Color[] colors = new Color[numColors];
		
		
		int offset = b.getWidth()/numColors;
		
		for(int i = 0; i < numColors; i++){
			int color = b.getRGB((i*offset),0);
			colors[i] = new Color(color);
		}
		
		return colors;
	}
	
	/**
	 * Returns a 1 or -1 with a probability of 50%
	 * @return 1 or -1
	 */
	public static int getRandomSign(){
		int num = (int) (Math.random()*50);
		if(num % 2 == 0) num = -1;
		else num = 1;
		
		return num;
	}




}
