package tester;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;

import kobaltBasic.RotatingActor;

public class Boid extends RotatingActor {


	/**
	 * Creates a new Boid represented by an isosceles triangle with the given centroid, base and height
	 * @param x X coordinate of the centroid
	 * @param y Y coordinate of the centroid
	 * @param base Base of the triangle representing the boid
	 * @param height Height of the triangle representing hhe boid
	 */
	public Boid(int x, int y, int base, int height) {
		super(x,y);
	

		int[] xpoints = {-(base/2),(base/2),0};
		int[] ypoints = {height/3,height/3,-(2*height)/3};
		setBounds(new Area(new Polygon(xpoints, ypoints, 3)));
	}


	@Override
	public void render(Graphics2D g) {
		this.renderBounds(g);		
	}
	
	public double getDistance(Boid other){
		double v = (x-other.x)*(x-other.x);
		v += (y-other.y)*(y-other.y);
		return Math.sqrt(v);
	}

}
