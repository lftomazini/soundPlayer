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
import java.awt.HeadlessException;
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

    public static final double SAMPLE_RATE = 44100.0;
    public static final int CHANNELS = 1;
    public static final boolean IS_BIG_ENDIAN = true;
    public static final int NUMBER_OF_BITS = 16;
    public static final boolean SIGNED = false;
    public static final String HOME = System.getProperty("user.home");

    private String s_path;
    private Path path;
    private byte[] bytes;
    private AudioFileFormat aFFormat;
    private AudioFormat aFormat;
    private double duration;
    private AudioInputStream aIS;

    public WAVAudioFile() throws IOException/*, UnsupportedAudioFileException */ {
    }

    public void chooseFile() {
        try {
            JFileChooser chooser = new JFileChooser();
            Component j = null;
            chooser.showOpenDialog(j);
            File file = chooser.getSelectedFile();
            this.s_path = file.getAbsolutePath();
            this.path = Paths.get(s_path);
            this.bytes = this.WAVtoByte();
        } catch (HeadlessException e) {
            System.out.println("Headless exception occurred here");
        }
    }

    public void choose(String s_path) {
        try {
            this.s_path = s_path;
            this.path = Paths.get(s_path);
        } catch (Exception e) {
            System.out.println("Exception occurred here");
        }
    }

    public String getS_path() {
        return this.s_path;
    }

    public Path getPath() {
        return this.path;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public double getDuration() {
        return this.duration;
    }

    public AudioFormat getAFormat() {
        return this.aFormat;
    }

    public AudioInputStream getAIS() {
        return this.aIS;
    }

    public AudioFileFormat getaFormat() {
        return this.aFFormat;
    }

    public void playMusic() {
        try {
        InputStream in = new FileInputStream(this.getS_path());
        AudioStream sample = new AudioStream(in);
        AudioPlayer.player.start(sample);
        } catch (FileNotFoundException f) {
            System.out.println("Invalid file path");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        } 
    }

    public void createSine() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What should be the duration of the waveform?");
            double dur = scanner.nextDouble();
            System.out.println("What should be the frequency of the waveform?");
            float frequency = scanner.nextFloat();
            System.out.println("What should be the amplitude of the waveform?");
            float amplitude = scanner.nextFloat();
            double twoPiF = 2
                            * Math.PI
                            * frequency;
            byte[] bytes
                   = new byte[(int) (dur
                                     * 2 * SAMPLE_RATE)];
            for (int i = 0; i < bytes.length; i++) {
                double time = i / SAMPLE_RATE;
                bytes[i] = (byte) (amplitude
                                   * Math.sin(twoPiF
                                              * time));

                InputStream buffer = new ByteArrayInputStream(bytes);
                this.aFormat = new AudioFormat((float) SAMPLE_RATE, NUMBER_OF_BITS,
                                               CHANNELS,
                                               SIGNED, IS_BIG_ENDIAN);
                this.aIS = new AudioInputStream(buffer, this.getAFormat(),
                                                bytes.length);
                buffer.close();
            }
            this.choose(HOME + "/sine.wav");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * http://blogs.msdn.com/b/dawate/archive/2009/06/25/intro-to-audio-programming-part-4-algorithms-for-different-sound-waves-in-c.aspx
     *
     * 
     */
    public void createSquare() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What should be the duration of the waveform?");
            double dur = scanner.nextDouble();
            System.out.println("What should be the frequency of the waveform?");
            float frequency = scanner.nextFloat();
            System.out.println("What should be the amplitude of the waveform?");
            float amplitude = scanner.nextFloat();

            double twoPiF = 2
                            * Math.PI
                            * frequency;
            byte[] bytes
                   = new byte[(int) (dur * 2
                                     * SAMPLE_RATE)];
            for (int i = 1; i < bytes.length; i++) {
                double time = i / SAMPLE_RATE;
                bytes[i] = (byte) (amplitude * Math.signum(Math.sin(i * frequency)));

                InputStream buffer = new ByteArrayInputStream(bytes);
                this.aFormat = new AudioFormat((float) SAMPLE_RATE, NUMBER_OF_BITS,
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

    public void createSawTooth() {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What should be the duration of the waveform?");
            double duration = scanner.nextDouble();
            System.out.println("What should be the frequency of the waveform?");
            float frequency = scanner.nextFloat();
            System.out.println("What should be the amplitude of the waveform?");
            float amplitude = scanner.nextFloat();

            byte[] bytes
                   = new byte[(int) (duration * 2
                                     * SAMPLE_RATE)];
            for (int i = 1; i < bytes.length; i++) {
                bytes[i] = (byte) ((byte) (amplitude * (i % (SAMPLE_RATE / frequency)) / (SAMPLE_RATE / frequency)) - 1);

                InputStream buffer = new ByteArrayInputStream(bytes);
                this.aFormat = new AudioFormat((float) SAMPLE_RATE, NUMBER_OF_BITS,
                                               CHANNELS,
                                               SIGNED, IS_BIG_ENDIAN);
                this.aIS = new AudioInputStream(buffer, this.getAFormat(),
                                                bytes.length);
                buffer.close();
            }
            this.choose(HOME + "/sawtooth.wav");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * @author -
     * http://stackoverflow.com/questions/13802441/how-to-convert-wav-file-into-byte-array
     * @return
     */
    public byte[] WAVtoByte() {
        byte [] num = new byte[0];
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

    public void byteToWav(String output) {
        try {
            AudioSystem.write(this.getAIS(),
                          AudioFileFormat.Type.WAVE,
                          new File(HOME, output));
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }    
    }

    /*
     * @author - http://0110.be/releases/TarsosDSP/TarsosDSP-1.6/TarsosDSP-1.6-Manual.pdf
     */
    public void delay() {
        double delayInSec;
        double decay;

        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Which should be the delay in seconds (less than 1 second)?");
        delayInSec = scanner.nextDouble();
        System.out.println("Which should be the decay? (Value between 0 and 1)");
        decay = scanner.nextDouble();

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
            AudioSystem.write(audio, AudioFileFormat.Type.WAVE, file);
            audio.close();

        } catch (UnsupportedAudioFileException e) {
            System.out.println(
                    "Invalid Input File: Cannot be converetd to an Audio File");
        } catch (IOException o) {
            System.out.println("IO Exception occurred");
        }
    }

    /**
     * @author - https://docs.oracle.com/javase/tutorial/sound/converters.html
     * @return - void
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
                    "byte order (big-endian or little-endian) :" + endian);
        } catch (UnsupportedAudioFileException  e) {
            System.out.println("Invalid audio file path");
        } catch (IOException i) {
            System.out.println("IOException occurred");
        }
    }

    /**
     *
     * http://stackoverflow.com/questions/7546010/obtaining-an-audioinputstream-upto-some-x-bytes-from-the-original-cutting-an-au
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

    public void save() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Where do you want to save?");
        String location = scanner.next();
        System.out.println(
                "What will be the name of the file? (don't need to add .wav)");
        String name = scanner.next();
        String fullPath = location + "/" + name + ".wav";
        this.copyAudio(fullPath);
    }

    /**
     * http://stackoverflow.com/questions/21732090/java-downsampling-from-22050-to-8000-gives-zero-bytes
     *
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    public void downsample() {
        
        try {
            String outputName = "downsample.wav";
            File file = new File(this.getS_path());
            File output = new File(HOME, outputName);

            Scanner scanner = new Scanner(System.in);
            float newFrequency;
            AudioInputStream ais;
            AudioInputStream finalInputStream = null;
            ais = AudioSystem.getAudioInputStream(file);
            AudioFormat sourceFormat = ais.getFormat();

            do {
                System.out.println(
                        "What should be the frequency to downsample? Currently it is " + ais.getFormat().getSampleRate());
                newFrequency = scanner.nextFloat();
                if (ais.getFormat().getSampleRate() < newFrequency) {
                    System.out.println(
                            "Invalid frequency! It should be less than " + ais.getFormat().getSampleRate());
                }
            } while (ais.getFormat().getSampleRate() < newFrequency);
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

    /**
     * @author1 -
     * http://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
     * @author2 -
     * http://0110.be/releases/TarsosDSP/TarsosDSP-1.6/TarsosDSP-1.6-Manual.pdf
     */
    public void controlVolume() {
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
        System.out.println("Outside the loop");


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
            AudioSystem.write(audio, AudioFileFormat.Type.WAVE, out);
            audio.close();

        } catch (UnsupportedAudioFileException e) {
            System.out.println(
                    "Invalid Input File: Cannot be converetd to an Audio File");
        } catch (IOException e) {
            System.out.println("IO Exception occurred");
        }
    }
}
