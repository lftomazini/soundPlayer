/* *****************************************
 * CSCI205 - Software Engineering and Design
 *
 * Fall 2015
 *
 * Name: Luis Felipe Franco Candeo Tomazini & Anmol Singh
 * Date: Oct 18, 2015
 * Time: 4:27:58 PM
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
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.fail;
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
     * Test of getDuration method, of class WAVAudioFile.
     */
    @Test
    public void testGetDuration() throws IOException {
        System.out.println("getDuration");
        WAVAudioFile instance = new WAVAudioFile();
        instance.choose(
                HOME + " john_cena.wav");
        int expResult = 7;
        int result = (int) instance.getDuration();
        Assert.assertEquals(expResult, result, EPSILON);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAFormat method, of class WAVAudioFile.
     */
    @Test
    public void testGetAFormat() {
        System.out.println("getAFormat");
        WAVAudioFile instance = new WAVAudioFile();
        AudioFormat expResult = null;
        AudioFormat result = instance.getAFormat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAIS method, of class WAVAudioFile.
     */
    @Test
    public void testGetAIS() {
        System.out.println("getAIS");
        WAVAudioFile instance = new WAVAudioFile();
        AudioInputStream expResult = null;
        AudioInputStream result = instance.getAIS();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getaFormat method, of class WAVAudioFile.
     */
    @Test
    public void testGetaFormat() {
        System.out.println("getaFormat");
        WAVAudioFile instance = new WAVAudioFile();
        AudioFileFormat expResult = null;
        AudioFileFormat result = instance.getaFormat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playMusic method, of class WAVAudioFile.
     */
    @Test
    public void testPlayMusic() {
        System.out.println("playMusic");
        WAVAudioFile instance = new WAVAudioFile();
        instance.playMusic();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of WAVtoByte method, of class WAVAudioFile.
     */
    @Test
    public void testWAVtoByte() {
        System.out.println("WAVtoByte");
        WAVAudioFile instance = new WAVAudioFile();
        byte[] expResult = null;
        byte[] result = instance.WAVtoByte();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of byteToWAV method, of class WAVAudioFile.
     */
    @Test
    public void testByteToWAV() {
        System.out.println("byteToWAV");
        String output = "";
        WAVAudioFile instance = new WAVAudioFile();
        instance.byteToWAV(output);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of askDelay method, of class WAVAudioFile.
     */
    @Test
    public void testAskDelay() {
        System.out.println("askDelay");
        WAVAudioFile instance = new WAVAudioFile();
        instance.askDelay();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delay method, of class WAVAudioFile.
     */
    @Test
    public void testDelay() {
        System.out.println("delay");
        double delayInSec = 0.0;
        double decay = 0.0;
        WAVAudioFile instance = new WAVAudioFile();
        instance.delay(delayInSec, decay);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInfo method, of class WAVAudioFile.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        WAVAudioFile instance = new WAVAudioFile();
        instance.getInfo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyAudio method, of class WAVAudioFile.
     */
    @Test
    public void testCopyAudio() {
        System.out.println("copyAudio");
        String destinationFileName = "";
        WAVAudioFile instance = new WAVAudioFile();
        instance.copyAudio(destinationFileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class WAVAudioFile.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        WAVAudioFile instance = new WAVAudioFile();
        instance.save();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of askDownsample method, of class WAVAudioFile.
     */
    @Test
    public void testAskDownsample() throws Exception {
        System.out.println("askDownsample");
        WAVAudioFile instance = new WAVAudioFile();
        float expResult = 0.0F;
        float result = instance.askDownsample();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDownsample method, of class WAVAudioFile.
     */
    @Test
    public void testCreateDownsample() {
        System.out.println("createDownsample");
        float newFrequency = 0.0F;
        WAVAudioFile instance = new WAVAudioFile();
        instance.createDownsample(newFrequency);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downsample method, of class WAVAudioFile.
     */
    @Test
    public void testDownsample() throws Exception {
        System.out.println("downsample");
        WAVAudioFile instance = new WAVAudioFile();
        instance.downsample();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of controlVolume method, of class WAVAudioFile.
     */
    @Test
    public void testControlVolume() {
        System.out.println("controlVolume");
        WAVAudioFile instance = new WAVAudioFile();
        instance.controlVolume();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
