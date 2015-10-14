/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw02;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author lffct001
 */
public class WAVClient extends WAVAudioFile {

    /**
     *
     * @throws java.io.IOException
     */
    public WAVClient() throws IOException {
        this.option = 100;
    }

    private int option;
    Scanner scanner = new Scanner(System.in);

    public void clearScreen() {
        for (int clear = 0; clear < 10; clear++) {
            System.out.println("\b");
        }
    }

    /**
     * Gives options to choose or generate an audio file
     *
     */
    public void menu() {

        do {

            /**
             * @param args the command line arguments
             */
            this.clearScreen();
            System.out.println("Choose an option:");
            System.out.println("(0) Exit");
            System.out.println("(1) Choose .wav file");
            System.out.println("(2) Generate .wav file");
            option = scanner.nextInt();

            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("You chose (1) Choose an option");
                    this.chooseFile();
                    this.actions();
                    break;
                case 2:
                    System.out.println("You chose (2) Generate .wav file");
                    this.chooseWaveForm();
                    break;
                default:
                    System.out.println(
                            "Option " + option + " is invalid. Choose another one.");
                    this.menu();
                    break;
            }
        } while (option != 0);
    }

    /**
     * Gives options to process the chosen or generated audio file
     */
    public void actions() {

        try {
            do {
                this.clearScreen();
                System.out.println("Choose an option:");
                System.out.println("(0) Exit");
                System.out.println("(1) Play .wav file");
                System.out.println("(2) Display information about the .wav file");
                System.out.println("(3) Downsample .wav file");
                System.out.println("(4) Adjust volume of the .wav file");
                System.out.println("(5) Add delay to .wav file");
                System.out.println("(6) Perform DFT to .wav file");
                System.out.println("(7) Save changes .wav file");
                System.out.println("(8) Back to previous menu");

                option = scanner.nextInt();
                switch (option) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("You chose (1) Play .wav file");
                        this.playMusic();
                        break;
                    case 2:
                        System.out.println(
                                "You chose (2) Display information about the .wav file");
                        this.getInfo();
                        break;
                    case 3:
                        System.out.println("You chose (3) Downsample .wav file");
                        this.downsample();
                        break;
                    case 4:
                        System.out.println(
                                "You chose (4) Adjust volume of the .wav file");
                        this.controlVolume();
                        this.choose(HOME + "/volume.wav");
                        break;
                    case 5:
                        System.out.println(
                                "You chose (5) Add delay to .wav file");
                        this.DFT();
                        this.choose(HOME + "/delay.wav");
                        break;
                    case 6:
                        System.out.println(
                                "You chose (6) Perform DFT to .wav file");
                        this.save();
                        break;
                    case 7:
                        System.out.println(
                                "You chose (7) Save changes .wav file");
                        this.save();
                        break;
                    case 8:
                        System.out.println(
                                "You chose (8) Back to previous menu");
                        this.menu();
                        break;
                    default:
                        System.out.println(
                                "Option " + option + " is invalid. Choose another one.");
                        this.actions();
                        break;
                }
            } while (option != 0);

        } catch (IOException e) {
            System.out.println("IOExcception occurred");
        }
    }

    /**
     * Gives options to choose the wave form to the audio file to be generated
     */
    public void chooseWaveForm() {
        do {
            this.clearScreen();
            System.out.println("Choose an option:");
            System.out.println("(0) Exit");
            System.out.println("(1) Genertae sine");
            System.out.println("(2) Genertae square");
            System.out.println("(3) Genertae sawtooth");
            System.out.println("(4) Back to previous menu");

            option = scanner.nextInt();
            switch (option) {

                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("You chose (1) Generate sine");
                    this.createSine();
                    this.byteToWAV("sine.wav");
                    this.choose(HOME + "/sine.wav");
                    this.actions();
                    break;
                case 2:
                    System.out.println(
                            "You chose (2) Generate square");
                    this.createSquare();
                    this.byteToWAV("square.wav");
                    this.choose(HOME + "/square.wav");
                    this.actions();
                    break;
                case 3:
                    System.out.println("You chose (3) Generate sawtooth");
                    this.createSawTooth();
                    this.byteToWAV("sawtooth.wav");
                    this.choose(HOME + "/sawtooth.wav");
                    this.actions();
                    break;
                case 4:
                    System.out.println(
                            "You chose (4) Back to previous menu");
                    this.menu();
                default:
                    System.out.println(
                            "Option " + option + " is invalid. Choose another one.");
                    this.chooseWaveForm();
                    break;
            }
        } while (option != 0);
    }

    public static void main(String[] args) {
        try {
            WAVClient menu = new WAVClient();
            menu.menu();
        } catch (IOException e) {
            System.out.println("IOExcception occurred");
        }
    }

}
