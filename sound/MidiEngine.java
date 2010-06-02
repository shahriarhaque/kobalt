package sound;
import javax.sound.midi.*;
import java.io.*;

/** Creates a new thread and plays the MIDI file on it **/
public class MidiEngine extends Thread {

    
    
    public void playFile(String fileName){
        
    	String file = fileName;
        if(!file.endsWith(".mid")) {
            helpAndExit();
        }
        File midiFile = new File(file);
        if(!midiFile.exists() || midiFile.isDirectory() || !midiFile.canRead()) {
            helpAndExit();
        }

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
            
            
        
        
            
            sequencer.open();
            sequencer.start();
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

            
        } catch(MidiUnavailableException mue) {
            System.out.println("Midi device unavailable!");
        } catch(InvalidMidiDataException imde) {
            System.out.println("Invalid Midi data!");
        } catch(IOException ioe) {
            System.out.println("I/O Error!");
        } 
    }

    /** Provides help message and exits the program */
    private static void helpAndExit() {
        System.out.println("Usage: java MidiPlayer midifile.mid");
        System.exit(1);
    }
}