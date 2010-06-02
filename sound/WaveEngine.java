package sound;

import java.util.HashMap;

/**
 * A class that loads all the given sound files into memory. Once a playback is requested it
 * plays the sound on a new thread.
 * @author Shahriar Haque
 *
 */

public class WaveEngine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static HashMap<String, WaveObject> map = new HashMap<String, WaveObject>();

	
	public static final void load(String name, String location){
		map.put(name, new WaveObject(location));
	}
	
	
	// plays a sound given the correct sound event constant
	public static void play(final String name){
		if(map.get(name) == null) return;
		
		
		new Thread(
				new Runnable() {
					public void run() {
						map.get(name).play();
					}
				}
		).start();


	}
	
}
