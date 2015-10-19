/* *****************************************
 * CSCI205 - Software Engineering and Design
 *
 * Fall 2015
 *
 * Name: Luis Felipe Franco Candeo Tomazini & Anmol Singh
 * Date: Oct 18, 2015
 * Time: 7:09:29 PM
 *
 * Project: csci205_HW
 * Package: hw02
 * File: WAVAudioFileTest
 * Description:
 *
 * ****************************************
 */
package hw02;

import static hw02.WAVAudioFile.HOME;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lffct001
 */
public class WAVAudioFileTest {

    public static final double EPSILON = 1.0E-10;

    public WAVAudioFileTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of delay method, of class WAVAudioFile.
     */
    @Test
    public void testDelay() throws IOException {
        System.out.println("delay");
        double delayInSec = 0.7;
        double decay = 0.4;
        WAVAudioFile instance = new WAVAudioFile();
        instance.choose(HOME + "/jown_cena.wav");
        byte[] initial = instance.getBytes();
        instance.delay(delayInSec, decay);
        instance.choose(HOME + "/delay.wav");
        byte[] ending = instance.getBytes();
        Assert.assertFalse(initial == ending);
    }

    /**
     * Test of createDownsample method, of class WAVAudioFile.
     */
    @Test
    public void testCreateDownsample() throws IOException, UnsupportedAudioFileException {
        System.out.println("createDownsample");
        float newFrequency = 8000.0F;

        WAVAudioFile instance = new WAVAudioFile();
        instance.choose(HOME + "john_cena.wav");

        instance.createDownsample(newFrequency);
        instance.choose(HOME + "/downsample.wav");
        File file = new File(HOME + "/downsample.wav");

        AudioInputStream ais;
        ais = AudioSystem.getAudioInputStream(file);

        Assert.assertEquals(newFrequency, ais.getFormat().getSampleRate(),
                            EPSILON);
    }

    /**
     * Test of controlVolume method, of class WAVAudioFile.
     */
    @Test
    public void testControlVolume() throws IOException {
        System.out.println("controlVolume");
        WAVAudioFile instance = new WAVAudioFile();
        instance.choose(HOME + "/jown_cena.wav");
        byte[] initial = instance.getBytes();
        int option_1 = 1;
        float option_2 = 80;

        instance.controlVolume(option_1, option_2);
        instance.choose(HOME + "/volume.wav");
        byte[] ending = instance.getBytes();
        Assert.assertFalse(initial == ending);

        instance.choose(HOME + "/jown_cena.wav");
        initial = instance.getBytes();
        option_1 = 2;
        instance.controlVolume(option_1, option_2);
        instance.choose(HOME + "/volume.wav");
        ending = instance.getBytes();
        Assert.assertFalse(initial == ending);
    }
}
