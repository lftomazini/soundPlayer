/* ***************************************** 
 * CSCI205 - Software Engineering and Design 
 * 
 * Fall 2015 
 * 
 * Name: Luis Felipe Franco Candeo Tomazini & Anmol Singh
 * Date: Oct 7, 2015
 * Time: 10:28:17 AM 
 *  
 * Project: csci205_HW
 * Package: hw01
 * File: WAVAudioFile
 * Description: 
 *  
 * ****************************************
 */
package hw01;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.nio.file.Files.readAllBytes;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.JFileChooser;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author as062 
 */
enum tone {
    SINE("sine"), SQUARE("square"), TRIANGLE("traingle");
     
    private String label;
    
    tone(String s) {
        this.label = s;
        
    }
    @Override
    public String toString() {
        return this.label;
    }
    
} 

public class WAVAudioFile {
    
    private String s_path;
    private Path path;
    
      
    public WAVAudioFile(){
        try {
            JFileChooser chooser = new JFileChooser();
            Component j = null;
            chooser.showOpenDialog(j);
            File file = chooser.getSelectedFile();
            this.s_path = file.getAbsolutePath();
            this.path = Paths.get(s_path);
//            Clip clip = AudioSystem.getClip();
//            clip.open(AudioSystem.getAudioInputStream(file));
//            clip.start();
//            Thread.sleep(clip.getMicrosecondLength() / 1000);

        } catch (Exception e) {
        }
    }
    
    public String getS_path(){
        return this.s_path;
    }
    
    public void playMusic() throws FileNotFoundException, IOException {
        InputStream in = new FileInputStream(this.getS_path());
        AudioStream sample = new AudioStream(in);
        AudioPlayer.player.start(sample);
    }
    /**
     * @author - http://stackoverflow.com/questions/13802441/how-to-convert-wav-file-into-byte-array 
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public byte[] WAVtoByte() throws FileNotFoundException, IOException {
        File file = new File(this.getS_path());
        InputStream fis = new FileInputStream(file);
        byte [] buffer = new byte[(int)file.length()];
        fis.read(buffer,0,buffer.length);
        fis.close();
        return buffer;
    }
    //public WAVAudioFile(float freq, float amp, String s){
        
    //}
    
    public static void main(String[] args) throws IOException {
        
        int volume  = -50;
        WAVAudioFile audio = new WAVAudioFile();
        audio.playMusic();
        File file = new File(audio.getS_path());
        try (InputStream fis = new FileInputStream(file)) {
            byte [] buffer = new byte[(int)file.length()];
            fis.read(buffer,0,buffer.length);
            fis.close();
            for (int i = 0; i < buffer.length; i+=2) {
                short audioSample = (short) (((buffer[i+1] & 0xff) << 8) | (buffer[i] & 0xff));
                audioSample = (short) (audioSample * (1 + (volume/100)));
                buffer[i] = (byte) audioSample;
                buffer[i+1] = (byte) (audioSample >> 8);
            }
                // Create the AudioData object from the byte array
            
            System.out.println("Exited the loop");
            AudioData audiodata = new AudioData(buffer);
            // Create an AudioDataStream to play back
            AudioDataStream audioStream = new AudioDataStream(audiodata);
            // Play the sound
            Thread.sleep(4000);
            System.out.println("Now, playing");
            AudioPlayer.player.start(audioStream);

            //readAllBytes(audio.path);
        } catch (Exception e) {
            
        }
        
        
        
    }
    
}
    

