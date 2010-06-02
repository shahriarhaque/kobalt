package sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * A class that loads all the given mp3 files into memory. Once a playback is requested it
 * plays the sound on a new thread.
 * @author Shahriar Haque
 *
 */

public class MP3Engine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static HashMap<String, Player> map = new HashMap<String, Player>();


	public static final void load(String name, String location){
		try {
			Player p = new Player(new FileInputStream(new File(location)));
			map.put(name, p);
		} 


		catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}

	}


	
	public static void play(final String name){
		if(map.get(name) == null) return;

		Player p = map.get(name);
		try {
			p.play();
		} 


		catch (JavaLayerException e) {
			System.err.println("Could not play mp3 file");
		}


	}


	public static void close(final String name){
		if(map.get(name) == null) return;

		Player p = map.get(name);
		p.close();
	}
	

}
