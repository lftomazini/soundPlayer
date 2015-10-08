/* ***************************************** 
 * CSCI205 - Software Engineering and Design 
 * 
 * Fall 2015 
 * 
 * Name: Luis Felipe Franco Candeo Tomazini & Anmol Singh
 * Date: Oct 7, 2015
 * Time: 7:35:55 PM 
 *  
 * Project: csci205_HW
 * Package: hw01
 * File: WAVProcessing
 * Description: 
 *  
 * ****************************************
 */
package hw01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author as062
 */
public class WAVProcessing {
    
    
    public byte[] WAVtoByte(WAVAudioFile e) throws FileNotFoundException, IOException {
        File file = new File(e.getS_path());
        InputStream fis = new FileInputStream(file);
        byte [] buffer = new byte[(int)file.length()];
        fis.read(buffer,0,buffer.length);
        fis.close();
        return buffer;
    }
    
    
    public static void main(String [] args) throws IOException{
        WAVAudioFile audio = new WAVAudioFile();
        audio.playMusic();
        byte [] output;
        output = audio.WAVtoByte();
        } 
    }

