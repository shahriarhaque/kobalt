package kobaltBasic;

import java.awt.image.BufferedImage;

/**
 * A masked image is an image that has different levels of transparency in different areas.
 * The transparency (alpha) value is set using a mask image. Mask images, ideally, are gray-scale
 * images. Black areas represent opaque, while white represent total transparency. Different
 * shaded of gray are used to represent different levels of translucency. Masked images are useful
 * for getting fine smoothened images for arbitrarily shaped sprites. Rotation of masked image
 * sprites are also of very high quality. However, using large masked images in your game can
 * drop your frame rate heavily.
 * @author Shahriar Haque
 *
 */
public class MaskedImage {
	
	private BufferedImage img,aMask;
	
	private BufferedImage maskedImage;
	
	/**
	 * Constructs a MaskedImage object based on the source image and using the gray-values
	 * from the alphaMask
	 * @param image The orginal colored source image.
	 * @param alphaMask The gray scale mask for the source image.
	 */
	public MaskedImage(BufferedImage image, BufferedImage alphaMask){
		img = image;
		aMask = alphaMask;
		
		maskedImage = Utilities.applyAlphaMask(img, aMask);
	}
	
	/**
	 * Constructs a MaskedImage object given the locations of the source and alpha mask images.
	 * @param imgPath Relative path to the source image.
	 * @param maskPath Relative path to the alpha mask.
	 */
	public MaskedImage(String imgPath, String maskPath){
		img = Utilities.load(imgPath);
		aMask = Utilities.load(maskPath);
		
		maskedImage = Utilities.applyAlphaMask(img, aMask);
	}
	
	/**
	 * Returns the original (un-altered) source image.
	 * @return Source Image
	 */
	public BufferedImage getImage() { return img; }
	
	/**
	 * Returns a BufferedImage on which the alpha mask has been applied.
	 * @return An alpha-masked image.
	 */
	public BufferedImage getMaskedImage() { return maskedImage; }
	
	

}
