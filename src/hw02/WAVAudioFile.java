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
package hw02;

import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Scanner;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author as062
 */
public class WAVAudioFile {

    /**
     *
     */
    public static final double SAMPLE_RATE = 44100.0;

    /**
     *
     */
    public static final int CHANNELS = 1;

    /**
     *
     */
    public static final boolean IS_BIG_ENDIAN = true;

    /**
     *
     */
    public static final int NUMBER_OF_BITS = 16;

    /**
     *
     */
    public static final boolean SIGNED = false;

    /**
     *
     */
    public static final String HOME = System.getProperty("user.home");

    private String s_path;
    private Path path;
    private byte[] bytes;
    private AudioFileFormat aFFormat;
    AudioFormat aFormat;
    double duration;
    AudioInputStream aIS;
    int numDownSamples;

    /**
     * Class Constructor
     *
     * @throws IOException
     */
    public WAVAudioFile() throws IOException {
    }

    /**
     * Chooses a file from the computer chosen by the user
     */
    public void chooseFile() {
//        try {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Type the file location");
//            this.s_path = scanner.next();
//            this.path = Paths.get(s_path);
//            this.bytes = this.WAVtoByte();
//        this.numDownSamples = 0;
//        } catch (HeadlessException e) {
//            System.out.println("Headless exception occurred here");
//        }

        try {
            JFileChooser chooser = new JFileChooser();
            Component j = null;
            chooser.showOpenDialog(j);
            File file = chooser.getSelectedFile();
            this.s_path = file.getAbsolutePath();
            this.path = Paths.get(s_path);
            this.bytes = this.WAVtoByte();
            this.numDownSamples = 0;
        } catch (Exception e) {
        }
    }

    /**
     * Chooses a file from the computer
     *
     * @param s_path
     */
    public void choose(String s_path) {
        try {
            this.s_path = s_path;
            this.path = Paths.get(s_path);
            this.bytes = this.WAVtoByte();
            this.numDownSamples = 0;
        } catch (Exception e) {
            System.out.println("Exception occurred here");
        }
    }

    /**
     *
     * @return the string containing the path to the file
     */
    public String getS_path() {
        return this.s_path;
    }

    /**
     *
     * @return the path to the file
     */
    public Path getPath() {
        return this.path;
    }

    /**
     *
     * @return the number of bytes in the audio file
     */
    public byte[] getBytes() {
        return this.bytes;
    }

    /**
     *
     * @return the duration in seconds of the audio file
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     *
     * @return the audio format of the audio file
     */
    public AudioFormat getAFormat() {
        return this.aFormat;
    }

    /**
     *
     * @return the audio input stream of the audio file
     */
    public AudioInputStream getAIS() {
        return this.aIS;
    }

    /**
     *
     * @return the audio file format of the audio file
     */
    public AudioFileFormat getaFormat() {
        return this.aFFormat;
    }

    /**
     *
     */
    public int getNumDownSamples() {
        return this.numDownSamples;
    }

    /**
     * Plays the audio file selected
     *
     */
    public void playMusic() {
        try {
            InputStream in = new FileInputStream(this.getS_path());
            AudioStream sample = new AudioStream(in);
            AudioPlayer.player.start(sample);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Press any key to go back to menu");
            if (scanner.hasNext()) {
                AudioPlayer.player.stop(sample);
            }
        } catch (FileNotFoundException f) {
            System.out.println("Invalid file path");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * Converts an array of bytes to a .WAV file
     *
     * @see
     * http://stackoverflow.com/questions/13802441/how-to-convert-wav-file-into-byte-array
     *
     * @return an array of bytes corresponding to a .WAV file
     */
    public byte[] WAVtoByte() {
        byte[] num = new byte[0];
        try {
            File file = new File(this.getS_path());
            InputStream inp_str = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inp_str.read(buffer, 0, buffer.length);
            inp_str.close();
            return buffer;
        } catch (FileNotFoundException f) {
            System.out.println("Invalid path to the file");
        } catch (IOException e) {
            System.out.println("IO Exception occurred");
        }
        return num;
    }

    /**
     * Converts a .WAV file to an array of bytes
     *
     * @param output the name of the file to be generated
     */
    public void byteToWAV(String output) {
        try {
            AudioSystem.write(this.getAIS(),
                              AudioFileFormat.Type.WAVE,
                              new File(HOME, output));
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    public void askDelay() {
        double delayInSec;
        double decay;

        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Which should be the delay in seconds (less than 1 second)?");
        delayInSec = scanner.nextDouble();
        System.out.println("Which should be the decay? (Value between 0 and 1)");
        decay = scanner.nextDouble();

        delay(delayInSec, decay);
    }

    /**
     * Adds a delay to a sound
     *
     * @see
     * http://0110.be/releases/TarsosDSP/TarsosDSP-1.6/TarsosDSP-1.6-Manual.pdf
     *
     */
    public void delay(double delayInSec, double decay) {

        try {

            File file = new File(this.getS_path());
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            int sample_rate = (int) audio.getFormat().getSampleRate();

            int sampleDelay = sample_rate * (int) delayInSec;
            byte[] buffer = this.getBytes();
            for (int i = 0; i < buffer.length - sampleDelay; i++) {
                buffer[i + sampleDelay] += (short) ((float) buffer[i] * decay);
            }
            this.bytes = buffer;

            boolean bigEndian = false;
            boolean sign = true;
            int bits = audio.getFormat().getSampleSizeInBits();
            int channels = audio.getFormat().getChannels();
            AudioFormat format;
            format = new AudioFormat(audio.getFormat().getSampleRate(), bits,
                                     channels, sign, bigEndian);
            ByteArrayInputStream bais = new ByteArrayInputStream(this.getBytes());
            audio = new AudioInputStream(bais, format, this.getBytes().length);
            AudioSystem.write(audio, AudioFileFormat.Type.WAVE, new File(HOME,
                                                                         "delay.wav"));
            audio.close();

        } catch (UnsupportedAudioFileException e) {
            System.out.println(
                    "Invalid Input File: Cannot be converetd to an Audio File");
        } catch (IOException o) {
            System.out.println("IO Exception occurred");
        }
    }

    /**
     * Shows information about the audio file selected
     *
     * @see https://docs.oracle.com/javase/tutorial/sound/converters.html
     */
    public void getInfo() {

        File file = new File(this.getS_path());
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);

            int bytes_per_frame = audio.getFormat().getFrameSize();
            long frame_length = audio.getFrameLength();
            int channels = audio.getFormat().getChannels();
            float frame_rate = audio.getFormat().getFrameRate();
            float sample_rate = audio.getFormat().getSampleRate();
            int bits_per_sample = audio.getFormat().getSampleSizeInBits();

            //big endian vs little endian
            String endian;
            if (audio.getFormat().isBigEndian() == true) {
                endian = "big-endian";
            } else {
                endian = "little-endian";
            }

            System.out.println("Number of channels: " + channels);
            System.out.println("bits per sample:    " + bits_per_sample);
            System.out.println(
                    "Sample Rate (samples per second, per channel): " + sample_rate);
            System.out.println("Frame Rate (Frames per second): " + frame_rate);
            System.out.println("Length of waveform in frames: " + frame_length);
            System.out.println(
                    "byte order (big-endian or little-endian):" + endian);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Invalid audio file path");
        } catch (IOException i) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * Copies a file to another location
     *
     * @see
     * http://stackoverflow.com/questions/7546010/obtaining-an-audioinputstream-upto-some-x-bytes-from-the-original-cutting-an-au
     *
     * @param destinationFileName
     */
    public void copyAudio(String destinationFileName) {
        try {
            Path output = Paths.get(destinationFileName);
            Files.copy(this.getPath(), output, REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * Saves a modified audio file
     *
     * @throws IOException
     */
    public void save() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Where do you want to save?");
        String location = scanner.next();
        System.out.println(
                "What will be the name of the file? (don't need to add .wav)");
        String name = scanner.next();
        String fullPath = location + "/" + name + ".wav";
        this.copyAudio(fullPath);
    }

    public float askDownsample() throws UnsupportedAudioFileException, IOException {
        Scanner scanner = new Scanner(System.in);
        float newFrequency;
        String outputName = "downsample.wav";
        File file = new File(this.getS_path());
        File output = new File(HOME, outputName);

        AudioInputStream ais;
        AudioInputStream finalInputStream = null;
        ais = AudioSystem.getAudioInputStream(file);
        AudioFormat sourceFormat = ais.getFormat();

        AudioFileFormat sourceFileFormat = AudioSystem.getAudioFileFormat(
                file);
        AudioFileFormat.Type targetFileType = sourceFileFormat.getType();

        do {
            System.out.println(
                    "What should be the frequency to downsample? Currently it is " + ais.getFormat().getSampleRate());
            newFrequency = scanner.nextFloat();
            if (ais.getFormat().getSampleRate() < newFrequency) {
                System.out.println(
                        "Invalid frequency! It should be less than " + ais.getFormat().getSampleRate());
            }
        } while (ais.getFormat().getSampleRate() < newFrequency);

        return newFrequency;
    }

    /**
     * Downsamples an audio file based on a frequency
     *
     * @see
     * http://stackoverflow.com/questions/21732090/java-downsampling-from-22050-to-8000-gives-zero-bytes
     *
     */
    public void createDownsample(float newFrequency) {

        try {
            String outputName = "downsample.wav";
            File file = new File(this.getS_path());
            File output = new File(HOME, outputName);

            AudioInputStream ais;
            AudioInputStream finalInputStream = null;
            ais = AudioSystem.getAudioInputStream(file);
            AudioFormat sourceFormat = ais.getFormat();

            AudioFileFormat sourceFileFormat = AudioSystem.getAudioFileFormat(
                    file);
            AudioFileFormat.Type targetFileType = sourceFileFormat.getType();

            AudioFormat targetFormat = new AudioFormat(
                    sourceFormat.getEncoding(),
                    newFrequency,
                    sourceFormat.getSampleSizeInBits(),
                    sourceFormat.getChannels(),
                    sourceFormat.getFrameSize(),
                    newFrequency,
                    sourceFormat.isBigEndian());
            if (!AudioSystem.isFileTypeSupported(targetFileType) || !AudioSystem.isConversionSupported(
                    targetFormat, sourceFormat)) {
                throw new IllegalStateException("Conversion not supported!");
            }
            finalInputStream = AudioSystem.getAudioInputStream(targetFormat,
                                                               ais);
            int nWrittenBytes = 0;
            nWrittenBytes = AudioSystem.write(finalInputStream, targetFileType,
                                              output);
            this.choose(HOME + "/" + outputName);
        } catch (UnsupportedAudioFileException u) {
            System.out.println("Invalid path to the audio file");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    public void downsample() throws UnsupportedAudioFileException, IOException {
        if (this.getNumDownSamples() > 1) {
            System.out.println(
                    "Impossible to downsample! You can do it only once.");
        } else {
            float frequency = this.askDownsample();
            createDownsample(frequency);
        }
    }

    public void askVolume() {
        int input_1 = 0;
        float input_2 = 0;
        do {
            System.out.print(
                    "To increase the volume, enter 1; to decrease the volume, enter 2: ");
            Scanner in = new Scanner(System.in);
            input_1 = in.nextInt();
            if (input_1 != 1 && input_1 != 2) {
                System.out.println("Invalid input: Please enter 1 or 2");
            }
        } while (input_1 != 1 && input_1 != 2);

        do {
            System.out.print("Enter the percentage amount of volume change: ");
            Scanner in_2 = new Scanner(System.in);
            input_2 = in_2.nextFloat();
            if (input_2 < 0) {
                System.out.println("No negative numbers please");
            }
        } while (input_2 < 0 && input_2 > 100);
        controlVolume(input_1, input_2);
    }

    /**
     * Adjusts the volume of the audio file
     *
     * @see
     * http://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
     * @see
     * http://0110.be/releases/TarsosDSP/TarsosDSP-1.6/TarsosDSP-1.6-Manual.pdf
     */
    public void controlVolume(int input_1, float input_2) {

        for (int i = 0; i < this.getBytes().length; i += 2) {
            // convert byte pair to int
            short buf1 = this.bytes[i + 1];
            short buf2 = this.bytes[i];

            buf1 = (short) ((buf1 & 0xff) << 8);
            buf2 = (short) (buf2 & 0xff);
            short rep;
            if (input_1 == 1) {
                rep = (short) (buf1 | buf2);
                rep = (short) (rep * (1 + input_2 / 100));

            } else {
                rep = (short) (buf1 | buf2);
                rep = (short) (rep * (1 - input_2 / 100));
            }
            this.bytes[i] = (byte) rep;
            this.bytes[i + 1] = (byte) (rep >> 8);
        }

        try {
            File out = new File(this.getS_path());
            AudioInputStream audio = AudioSystem.getAudioInputStream(out);
            boolean bigEndian = false;
            boolean sign = true;
            int bits = audio.getFormat().getSampleSizeInBits();
            int channels = audio.getFormat().getChannels();
            AudioFormat format;
            format = new AudioFormat(audio.getFormat().getSampleRate(), bits,
                                     channels, sign, bigEndian);
            ByteArrayInputStream bais = new ByteArrayInputStream(this.getBytes());
            audio = new AudioInputStream(bais, format, this.getBytes().length);
            AudioSystem.write(audio, AudioFileFormat.Type.WAVE, new File(HOME,
                                                                         "volume.wav"));
            audio.close();

        } catch (UnsupportedAudioFileException e) {
            System.out.println(
                    "Invalid Input File: Cannot be converetd to an Audio File");
        } catch (IOException e) {
            System.out.println("IO Exception occurred");
        }
    }
}
