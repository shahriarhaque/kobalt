package tester;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import experimental.network.NetworkComponent;
import experimental.network.P2PService;


import kobaltBasic.BasicGame;
import kobaltBasic.KeyboardSupport;
import kobaltBasic.Sprite;
import kobaltBasic.Utilities;


public class P2PTester extends BasicGame implements KeyboardSupport, NetworkComponent {

	
	Player1 p1;
	Player1 p2;
	P2PService net;
	
	public P2PTester(String title, int w, int h) {
		super(title, w, h);
		
		
		
		BufferedImage img = Utilities.load("tester/resources/player.png");
		
		p1 = new Player1(new Sprite(img),100,300);
		p2 = new Player1(new Sprite(img),500,300);
		
		net = new P2PService(this);
		
		addKeyboardSupport(this);
		addNetworkService(net);
		net.startServer(8181);
		
	}


	@Override
	public void render(Graphics2D g) {
		fillWithColor(Color.BLACK);
		p1.render(g);
		p2.render(g);
	}

	@Override
	public void update() {
		p1.update();
		p2.update();
	}

	public String getData() {
		String s = p1.x_velocity + "," + p1.y_velocity;
		return s;
	}

	public void parseData(String msg) {
		String[] arr = msg.split(",");
		
		p2.x_velocity = Double.parseDouble(arr[0]);
		p2.y_velocity = Double.parseDouble(arr[1]);
	}
	
	public static void main(String[] args) {
		new P2PTester("P2P Tester", 640, 480).start();

	}


	public void handleKeyPress(int keyCode) {
		p1.handleKeyPress(keyCode);
		
	}


	public void handleKeyRelease(int keyCode) {
		p1.handleKeyRelease(keyCode);
		
		if(keyCode == KeyEvent.VK_ENTER){
			net.startSession();
		}
		
	}

}
