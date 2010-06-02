package tester;

import java.awt.Graphics2D;
import java.util.Random;

import kobaltBasic.BasicGame;

public class BoidTester extends BasicGame {

	static final int FLOCK_SIZE = 10;
	static final int LOCALITY_DISTANCE = 20;

	Boid[] flock = new Boid[FLOCK_SIZE];
	static final int bs = 16;
	static final int ht = 18;

	public BoidTester(String title, int w, int h) {
		super(title, w, h);	
		createFlock();
	}

	public void createFlock(){
		Random rd = new Random();

		for(int i = 0; i < flock.length; i++){

			flock[i] = new Boid(rd.nextInt(600),rd.nextInt(450), bs,ht);
			flock[i].heading = rd.nextInt(360);
			flock[i].velocity = 100;

		}

	}

	public void reposition(Boid boid){

		double m = 0;
		double b = 0;
		double theta = 0;
		double mdash= 0;
		double mdash2 = 0;

		double ix = 0;
		double iy = 0;

		if(boid.x > 640 || boid.y > 480 || boid.x < 0 || boid.y < 0){	

			theta = boid.heading-90;
			m = Math.tan(Math.toRadians(theta));
			b = boid.y - m*boid.x;

			if(b < 0){

				ix = (-b/m);
				iy = 0;

				mdash = 480/(640-ix);

				if(boid.y <= 0){
					if(m > mdash){
						boid.x = (480-b)/m;
						boid.y = 480;
					}

					else{
						boid.x = 640;
						boid.y = (640*m) + b;
					}	
				}

				else{
					boid.x = ix;
					boid.y = iy;
				}
			}

			else if(b >=0 && b <= 480){
				ix = 0;
				iy = b;

				mdash = -b/640; // top
				mdash2 = (480-b)/640; // bottom

				if(boid.x < 0){
					if(m < mdash){
						boid.x = -b/m;
						boid.y = 0;
					}

					else if(m > mdash2){
						boid.x = (480-b)/m;
						boid.y = 480;
					}

					else{
						boid.x = 640;
						boid.y = (640*m)+b;
					}
				}

				else{
					boid.x = ix;
					boid.y = iy;
				}

			}

			else{
				ix = (480-b)/m;
				iy = 480; 

				mdash = -480/(640-ix);

				if(boid.y >= 480){
					if(m < mdash){
						boid.x = -b/m;
						boid.y = 0;
					}

					else{
						boid.x = 640;
						boid.y = (640*m)+b;
					}	
				}	
				else{
					boid.x = ix;
					boid.y = iy;
				}				
			}
		}		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BoidTester("Boid Tester",640,480).start();

	}

	@Override
	public void render(Graphics2D g) {
		this.fillWithColor(BLACK);
		for(Boid b: flock){
			b.renderBounds(g);
			g.fillOval((int) b.x, (int) b.y, 2, 2);
//			g.setColor(Color.green);
//			g.drawOval((int)b.x, (int)b.y, 20, 20);
		}

	}
	
	
	public void applyRules(){
		double sum_heading = 0;
		double locality_size = 0;
		
		for(Boid b: flock){
			sum_heading = 0;
			locality_size = 0;
			
			for(Boid local: flock){
				
				// if within same locality
				if(b.getDistance(local) < LOCALITY_DISTANCE){
					locality_size++;
					
					// sum up heading
					sum_heading += local.heading;
				}
			}
			
			b.heading = (sum_heading/locality_size);
		}
		
		
	}

	@Override
	public void update() {
	
		 
		for(Boid b: flock){

			b.update();
			reposition(b);
			applyRules();
		}

	}

}
