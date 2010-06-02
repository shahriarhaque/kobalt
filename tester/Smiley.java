package tester;

import kobaltBasic.RotatingSpriteActor;
import kobaltBasic.Sprite;
import net.phys2d.math.ROVector2f;
import net.phys2d.raw.Body;

public class Smiley extends RotatingSpriteActor {

	Body body;
	
	public Smiley(Sprite s, Body b) {
		super(s, b.getPosition().getX(), b.getPosition().getY());
		body = b;
	}
	
	public void update(){
		ROVector2f pos = body.getPosition();
		float angle = body.getRotation();
		
		x = pos.getX()-50;
		y = pos.getY()-50;
		heading = Math.toDegrees(angle);
		
		super.update();
		
	}

}
