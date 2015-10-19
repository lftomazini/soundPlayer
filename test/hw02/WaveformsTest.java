/* *****************************************
 * CSCI205 - Software Engineering and Design
 *
 * Fall 2015
 *
 * Name: Luis Felipe Franco Candeo Tomazini & Anmol Singh
 * Date: Oct 18, 2015
 * Time: 4:48:26 PM
 *
 * Project: csci205_HW
 * Package: hw02
 * File: WaveformsTest
 * Description:
 *
 * ****************************************
 */
package hw02;

import static hw02.WAVAudioFile.HOME;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lffct001
 */
public class WaveformsTest {

    public static final double EPSILON = 1.0E-10;

    public WaveformsTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generateSine method, of class Waveforms.
     */
    @Test
    public void testGenerateSine() throws IOException {
        System.out.println("generateSine");
        double duration = 3.0;
        float frequency = 4000.0F;
        float amplitude = 0.3F;
        Waveforms instance = new Waveforms();
        instance.generateSquare(duration, frequency, amplitude);
        instance.choose(HOME + "square.wav");

        Assert.assertEquals(duration, instance.getDuration(), EPSILON);
    }

    /**
     * Test of generateSquare method, of class Waveforms.
     */
    @Test
    public void testGenerateSquare() throws IOException {
        System.out.println("generateSquare");
        double duration = 3.0;
        float frequency = 400.0F;
        float amplitude = 0.3F;
        Waveforms instance = new Waveforms();
        instance.generateSquare(duration, frequency, amplitude);
        instance.choose(HOME + "square.wav");

        Assert.assertEquals(duration, instance.getDuration(), EPSILON);
    }

    /**
     * Test of generateSawTooth method, of class Waveforms.
     */
    @Test
    public void testGenerateSawTooth() throws IOException {
        System.out.println("generateSawTooth");
        double duration = 3.0;
        float frequency = 400.0F;
        float amplitude = 0.3F;
        Waveforms instance = new Waveforms();
        instance.generateSquare(duration, frequency, amplitude);
        instance.choose(HOME + "square.wav");

        Assert.assertEquals(duration, instance.getDuration(), EPSILON);;
    }

}
