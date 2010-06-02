package tester;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import kobaltBasic.BasicGame;

public class PhysicsTester extends BasicGame {
	
	World world;

	public PhysicsTester(String title, int w, int h) {
		super(title, w, h);

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
		
		Body body2 = new Body("Mover1", new Box(50.0f, 50.0f), 100.0f);
		body2.setPosition(250.0f, 4.0f);
		world.add(body2);
		Body body4 = new Body("Mover2", new Box(50.0f, 50.0f), 100.0f);
		body4.setPosition(230.0f, -60.0f);
		world.add(body4);
		Body body8 = new Body("Mover3", new Box(50.0f, 50.0f), 100.0f);
		body8.setPosition(280.0f, -120.0f);
		world.add(body8);
		
	}




	public void render(Graphics2D g) {
		
		fillWithColor(WHITE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		BodyList bodies = world.getBodies();
		
		for (int i=0;i<bodies.size();i++) {
			Body body = bodies.get(i);
			
			drawBody(g, body);
		}

	}
	
	protected void drawBody(Graphics2D g, Body body) {
		if (body.getShape() instanceof Box) {
			drawBoxBody(g,body,(Box) body.getShape());
		}

	}
	
	protected void drawBoxBody(Graphics2D g, Body body, Box box) {
		Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());
		
		Vector2f v1 = pts[0];
		Vector2f v2 = pts[1];
		Vector2f v3 = pts[2];
		Vector2f v4 = pts[3];
		
		g.setColor(Color.black);
		g.drawLine((int) v1.x,(int) v1.y,(int) v2.x,(int) v2.y);
		g.drawLine((int) v2.x,(int) v2.y,(int) v3.x,(int) v3.y);
		g.drawLine((int) v3.x,(int) v3.y,(int) v4.x,(int) v4.y);
		g.drawLine((int) v4.x,(int) v4.y,(int) v1.x,(int) v1.y);
	}

	public void update() {
		for(int i = 0; i < 5; i++)world.step();
	}

		
	
	public static void main(String[] args) {
		new PhysicsTester("Physics Tester",640,480).start(); // creates a new game window of size 640x480 and starts the game loop.

	}

}
