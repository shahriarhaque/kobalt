package effects;


import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kobaltBasic.BasicGame;
import kobaltBasic.SpriteActor;

public class FadeEffect {


	/** Flag that indicates whether the animation has terminated or not. **/
	public boolean done = false;

	/** Fade Out constant **/
	public static final int FADE_OUT = 1;
	/** Fade In constant**/
	public static final int FADE_IN = 0;

	/** Current type of fade effect**/
	public int type = 0;

	/** Duration in milliseconds **/
	public int duration = 1000;

	private int elapsed = 0;

	/**
	 * Creates a FadeEffect object of the given type
	 * @param type Either FadeEffect.FADE_IN or FadeEffect.FADE_OUT. Any other value will result in a fade out effect.
	 */
	public FadeEffect(int type){
		this.type = type;
	}


	/**
	 * Resets internal fade effect counter to zero. Use this to initialize the FadeEffect object for a new run.
	 */
	public void reset(){
		elapsed = 0;
		done = false;
	}


	/**
	 * Applies the fade effect on the given BufferedImage on the graphics context {@code g}
	 * at location x,y;
	 * @param g The graphics context (background) on which the image will be drawn.
	 * @param b The BufferedImage on which the effect will be applied.
	 * @param x X coordinate of the image on the background.
	 * @param y Y coordinate of the image on the background.
	 */
	public void applyEffect(Graphics2D g, BufferedImage b, int x, int y){



		elapsed += BasicGame.interval;


		if(elapsed >= duration) {
			elapsed = duration;
			done = true;
		}

		// linear interpolation of alpha value
		float amount = ((float) (elapsed)) / duration;

		Composite oldComposite = g.getComposite();
		Composite fade = null;
		if(type == FADE_IN) fade = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, amount);
		else fade =  AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - amount  );

		g.setComposite(fade);
		g.drawImage(b, x, y, null);
		g.setComposite(oldComposite);


	}

	public void applyEffect(Graphics2D g, Drawable d){


		elapsed += BasicGame.interval;

		if(elapsed >= duration) {
			elapsed = duration;
			done = true;
		}

		// linear interpolation of alpha value
		float amount = ((float) (elapsed)) / duration;

		Composite oldComposite = g.getComposite();
		Composite fade = null;
		if(type == FADE_IN) fade = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, amount);
		else fade =  AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - amount  );

		g.setComposite(fade);
		d.draw(g); // delegates rendering to the Drawable
		g.setComposite(oldComposite);

	}





	/**
	 * Applies the fade effect on the current frame of the given GameEntity
	 * @param g The graphics context (background) on which the GameEntity will be drawn.
	 * @param entity The GameEntity on which the fade effect will be applied.
	 */
	public void applyEffect(Graphics2D g, SpriteActor entity){

		applyEffect(g, entity.theSprite.getCurrentFrame(), (int)entity.x, (int)entity.y);	

	}


}
