package kobaltBasic;

import java.awt.image.BufferedImage;

/**
 * A sprite is the visual representation of all game objects, let it be game characters, bonuses,
 * or even message screens. A sprite can hold either a single image, or a sequence of images.
 * Using the appropriate methods of the class you can change the ordering of the images and
 * specify what image to show at what time.
 * @author Shahriar Haque
 * 
 * BugID 001: Sprite not using indices from sequence array while getting next frame. (Fixed)
 *
 */
public class Sprite {
	
	private final  BufferedImage[] frames;
	private int[] sequence;
	
	private int currentFrame;
	
	/**
	 * Constructs a new Sprite object given the {@link BufferedImage} reference.
	 * @param b The BufferedImage which the Sprite will visually represent.
	 */
	public Sprite(BufferedImage b){
		frames = new BufferedImage[]{b};
		sequence = new int[]{0};
		
		currentFrame = sequence[0];
	}
	
	/**
	 * Constructs a new Sprite given an array of {@link BufferedImage} objects. The images are
	 * ordered on the same arrangment as it was in the passed array. If you wish to use a different
	 * arrangement, refer to the {@link #changeSequence(int[])} method on how to do so. By default,
	 * calling current frame points to the first image element of the passed array. Thus calling
	 * the {@link #getCurrentFrame()} returns the first element of the array if nothing else is
	 * changed between the constructor call and the method invocation.
	 * @param b An array of BufferedImage objects that the Sprite will use.
	 */
	public Sprite(BufferedImage[] b){
		frames = b;
		
		sequence = new int[frames.length];
		
		for(int i = 0; i < frames.length;i++) sequence[i] = i;
		
		currentFrame = sequence[0];
	}
	
	/**
	 * Initializes the Sprite object with the given {@link MaskedImage}. Internally, the constructor
	 * makes a call to the {@link MaskedImage#getMaskedImage() } method to get the reference to
	 * the image onto which the alpha mask has been applied to.
	 * @param m MaskedImage Object.
	 */
	public Sprite(MaskedImage m){
		frames = new BufferedImage[]{m.getMaskedImage()};
		sequence = new int[]{0};
		
		currentFrame = sequence[0];
	}
	
	/**
	 * Constructs a new Sprite object from an array of MaskedImage object. The current sequence
	 * of the sprite images is set to the same sequence as the the passed array. Invoking the
	 * {@link #getCurrentFrame()} will return the first image of the given array if nothing is
	 * changed between the constructor call and the method invocation. Refer to the
	 * {@link #Sprite(MaskedImage)} to know how the constructor works internally.
	 * @param m Array of MaskedImage objects.
	 */
	public Sprite(MaskedImage[] m){
		frames = new BufferedImage[m.length];
		
		for(int i = 0; i < frames.length; i++) { frames[i] = m[i].getMaskedImage(); }
		
		sequence = new int[frames.length];
		
		for(int i = 0; i < frames.length;i++) { sequence[i] = i; }
		
		currentFrame = sequence[0];
	}
	
	
	/**
	 * Changes the sequence of pictures to be used. If you instantiated this class using an
	 * array of images then the sequence in which that array was created will be used as the
	 * internal sequence for this sprite. However, by providing a new sequence in the form of
	 * an array of the same length as the BufferedImage[] array you started of with, you can
	 * specify a custom sequence. For example if you call this method using the parameter
	 * new int {2,3,1,4} then everytime you advance a frame, the sprite will read the next
	 * image's index from this sequence.
	 * @param newSequence int[] Integer array of the same length as the number of images in
	 * this sprite.
	 */
	public void changeSequence(int[] newSequence) {
		for(int i = 0; i < sequence.length; i++) sequence[i] = newSequence[i];
	}
	
	/**
	 * Returns the BufferedImage at the location given by the {@link #getFrameIndex()} method.
	 * @return BufferedImage Current image of the provided sequence.
	 */
	public BufferedImage getCurrentFrame() { return frames[sequence[currentFrame]]; }
	
	/**
	 * Returns an integer pointing to the index of the current frame within its sequence.
	 * @return int Index of current frame.
	 */
	public int getFrameIndex() { return currentFrame; }
	
	/**
	 * Makes the currentFrame point to the next image of the sequence.
	 */
	public void advanceFrame() { 
		currentFrame++;
		currentFrame %= sequence.length;
	}

}
