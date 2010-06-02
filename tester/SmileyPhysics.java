package tester;

import experimental.physics.AbstractPhysicsEngine;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

public class SmileyPhysics extends AbstractPhysicsEngine{

	public Body body2; // smiley body
	
	public void init() {
		world = new World(new Vector2f(0.0f, 10.0f), 10, new QuadSpaceStrategy(20,5));
		world.clear();
		world.setGravity(0,10);
		
		Body body1 = new StaticBody("Ground1", new Box(400.0f, 20.0f));
		body1.setPosition(250.0f, 400);
		world.add(body1);
		Body body3 = new StaticBody("Ground2", new Box(200.0f, 20.0f));
		body3.setPosition(360.0f, 380);
		world.add(body3);
		Body body5 = new StaticBody("Ground3", new Box(20.0f, 100.0f));
		body5.setPosition(200.0f, 300);
		world.add(body5);
		Body body6 = new StaticBody("Ground3", new Box(20.0f, 100.0f));
		body6.setPosition(400.0f, 300);
		world.add(body6);
		
		body2 = new Body("Mover1", new Box(100.0f, 100.0f), 10.0f);
		body2.setPosition(250.0f, 4.0f);
		world.add(body2);
//		Body body4 = new Body("Mover2", new Box(50.0f, 50.0f), 100.0f);
//		body4.setPosition(230.0f, -60.0f);
//		world.add(body4);
//		Body body8 = new Body("Mover3", new Box(50.0f, 50.0f), 100.0f);
//		body8.setPosition(280.0f, -120.0f);
//		world.add(body8);
		
	}

}
