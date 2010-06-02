package tester;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kobaltBasic.*;

public class TileTester extends BasicGame {

	Spritesheet tiles = new Spritesheet("tester/resources/tiles.png");
	BufferedImage img = null;
	
	public TileTester(String title, int w, int h) {
		super(title, w, h);
		
		
		tiles.imgWidth = 22;
		tiles.hOffset = 1;
		tiles.vOffset = 4;
		tiles.imgHeight = 24;
		tiles.numColumns = 5;
		tiles.numRows = 8;
		tiles.maskColor = new Color(191,220,191);
		
		tiles.extractAll("tester/resources/tiles.txt");
		
		BufferedImage buf[] = tiles.extractRow(0, 168, 22, 24, 2, 5);
		img = buf[4];

	}
	
	@Override
	public void render(Graphics2D g) {
		fillWithColor(BLACK);
	
		g.drawImage(img, 100, 100, null);
		
	}

	@Override
	public void update() {}


	public static void main(String[] args) {
		new TileTester("Tile Test Demo", 640, 480).start();
	}



}
