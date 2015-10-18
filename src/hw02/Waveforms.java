/* *****************************************
 * CSCI205 - Software Engineering and Design
 *
 * Fall 2015
 *
 * Name: Luis Felipe Franco Candeo Tomazini & Anmol Singh
 * Date: Oct 16, 2015
 * Time: 4:32:19 PM
 *
 * Project: csci205_HW
 * Package: hw02
 * File: Wafeforms
 * Description:
 *
 * ****************************************
 */
package hw02;

import static hw02.WAVAudioFile.CHANNELS;
import static hw02.WAVAudioFile.IS_BIG_ENDIAN;
import static hw02.WAVAudioFile.NUMBER_OF_BITS;
import static hw02.WAVAudioFile.SAMPLE_RATE;
import static hw02.WAVAudioFile.SIGNED;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

/**
 *
 * @author lffct001
 */
public class Waveforms extends WAVAudioFile {
    private Complex[] complexNums;

    public Waveforms() throws IOException {

    }

    /**
     * @return @see
     * http://www.nayuki.io/page/how-to-implement-the-discrete-fourier-transform
     *
     * Performs a DFT to the waveform currently loaded in memory
     *
     */
    public Complex[] DFT() {
        int n = this.complexNums.length;
        Complex[] output = null;
        for (int i = 0; i < n; i++) {  // For each output element
            float sumreal = 0;
            float sumimag = 0;
            for (int j = 0; j < n; j++) {  // For each input element
                float angle = (float) (2 * Math.PI * j * i / n);
                sumreal += this.complexNums[j].getReal() * Math.cos(angle) + this.complexNums[j].getImg() * Math.sin(
                        angle);
                sumimag += -this.complexNums[j].getReal() * Math.sin(angle) + this.complexNums[j].getImg() * Math.cos(
                        angle);
            }

            output[i].real = sumreal;
            output[i].img = sumimag;
        }
        return output;
    }

    /**
     * Asks the user for the duration of the waveform to be generated
     *
     * @return the duration of the waveform to be generated
     */
    public double getDurationWaveForms() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What should be the duration of the waveform?");
        double duration = scanner.nextDouble();

        return duration;
    }

    /**
     * Asks the user for the frequency of the waveform to be generated
     *
     * @return the frequency of the waveform to be generated
     */
    public float getFrequencyWaveForms() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What should be the frequency of the waveform?");
        float frequency = scanner.nextFloat();

        return frequency;
    }

    /**
     * Asks the user for the frequency of the waveform to be generated
     *
     * @return the frequency of the waveform to be generated
     */
    public float getAmplitudeWaveForms() {
        Scanner scanner = new Scanner(System.in);
        float amplitude;
        do {
            System.out.println(
                    "What should be the amplitude of the waveform?");
            amplitude = scanner.nextFloat();
            if (amplitude < 0.0 || amplitude > 1.0) {
                System.out.println(
                        "Invalid amplitude! It should be between 0.0 and 1.0");
            }
        } while (amplitude < 0.0 || amplitude > 1.0);
        amplitude *= 10;

        return amplitude;
    }

    /**
     * Generates a sine wave sound based on the parameters given
     *
     * @param duration
     * @param frequency
     * @param amplitude
     * @see
     * http://blogs.msdn.com/b/dawate/archive/2009/06/25/intro-to-audio-programming-part-4-algorithms-for-different-sound-waves-in-c.aspx
     *
     */
    public void generateSine(double duration, float frequency,
                             float amplitude) {
        try {
            double twoPiF = 2
                            * Math.PI
                            * frequency;
            byte[] bytes
                   = new byte[(int) (duration
                                     * 2 * SAMPLE_RATE)];
            for (int i = 0; i < bytes.length; i++) {
                double time = i / SAMPLE_RATE;
                bytes[i] = (byte) (amplitude
                                   * Math.sin(twoPiF
                                              * time));

                InputStream buffer = new ByteArrayInputStream(bytes);
                this.aFormat = new AudioFormat((float) SAMPLE_RATE,
                                               NUMBER_OF_BITS,
                                               CHANNELS,
                                               SIGNED, IS_BIG_ENDIAN);
                this.aIS = new AudioInputStream(buffer, this.getAFormat(),
                                                bytes.length);
                buffer.close();
            }
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * Asks the user for the parameters and generates a sine waveform
     */
    public void createSine() {
        double duration = this.getDurationWaveForms();
        float frequency = this.getFrequencyWaveForms();
        float amplitude = this.getAmplitudeWaveForms();

        this.generateSine(duration, frequency, amplitude);
    }

    /**
     * Generates a square wave sound based on the parameters given
     *
     * @param duration
     * @param frequency
     * @param amplitude
     * @see
     * http://blogs.msdn.com/b/dawate/archive/2009/06/25/intro-to-audio-programming-part-4-algorithms-for-different-sound-waves-in-c.aspx
     *
     */
    public void generateSquare(double duration, float frequency,
                               float amplitude) {
        try {
            double twoPiF = 2
                            * Math.PI
                            * frequency;
            byte[] bytes
                   = new byte[(int) (duration * 2
                                     * SAMPLE_RATE)];
            for (int i = 1; i < bytes.length; i++) {
                double time = i / SAMPLE_RATE;
                bytes[i] = (byte) (amplitude * Math.signum(Math.sin(
                                   i * frequency)));

                InputStream buffer = new ByteArrayInputStream(bytes);
                this.aFormat = new AudioFormat((float) SAMPLE_RATE,
                                               NUMBER_OF_BITS,
                                               CHANNELS,
                                               SIGNED, IS_BIG_ENDIAN);
                this.aIS = new AudioInputStream(buffer, this.getAFormat(),
                                                bytes.length);

                buffer.close();
            }
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * Asks the user for the parameters and generates a square waveform
     */
    public void createSquare() {
        double duration = this.getDurationWaveForms();
        float frequency = this.getFrequencyWaveForms();
        float amplitude = this.getAmplitudeWaveForms();

        this.generateSquare(duration, frequency, amplitude);

    }

    /**
     * Generates a sawtooth wave sound based on the parameters given
     *
     * @param duration
     * @param frequency
     * @param amplitude
     * @see
     * http://stackoverflow.com/questions/17604968/generate-sawtooth-tone-in-java-android
     *
     */
    public void generateSawTooth(double duration, float frequency,
                                 float amplitude) {
        try {
            byte[] bytes
                   = new byte[(int) (duration * 2
                                     * SAMPLE_RATE)];
            for (int i = 1; i < bytes.length; i++) {
                bytes[i] = (byte) ((byte) (amplitude * (i % (SAMPLE_RATE / frequency)) / (SAMPLE_RATE / frequency)) - 1);

                InputStream buffer = new ByteArrayInputStream(bytes);
                this.aFormat = new AudioFormat((float) SAMPLE_RATE,
                                               NUMBER_OF_BITS,
                                               CHANNELS,
                                               SIGNED, IS_BIG_ENDIAN);
                this.aIS = new AudioInputStream(buffer, this.getAFormat(),
                                                bytes.length);
                buffer.close();
            }
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * Asks the user for the parameters and generates a sawtooth waveform
     */
    public void createSawTooth() {
        double duration = this.getDurationWaveForms();
        float frequency = this.getFrequencyWaveForms();
        float amplitude = this.getAmplitudeWaveForms();

        this.generateSquare(duration, frequency, amplitude);

    }

}
