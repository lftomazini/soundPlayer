/* ***************************************** 
 * CSCI205 - Software Engineering and Design 
 * 
 * Fall 2015 
 * 
 * Name: Luis Felipe Franco Candeo Tomazini & Anmol Singh
 * Date: Oct 16, 2015
 * Time: 10:05:49 AM 
 *  
 * Project: csci205_HW
 * Package: hw02
 * File: Complex
 * Description: 
 *  
 * ****************************************
 */
package hw02;

import java.util.Arrays;

/**
 *
 * @author as062
 */
public class Complex {
    double real;
    double img;
    
    /**
     *
     * @param real - real component of the number
     * @param img - imaginary component of the number
     */
    public Complex(double real, double img) {
        this.real = real;
        this.img = img;
    }
    
    public double getReal(){
        return this.real;
    }
    
    public double getImg(){
        return this.img;
    }
    
    /**
     *  Adds two complex numbers
     * 
     * @param c - complex number
     * @return - result
     */
    public Complex add(Complex c) {
        Complex result = new Complex(0,0);
        result.img = this.img + c.img;
        result.real = this.real + c.real;
        return result;
    }
    
    /**
     * Subtracts two complex numbers
     * 
     * @param c - complex number
     * @return - result
     */
    public Complex Subtract(Complex c) {
        Complex result = new Complex(0,0);
        result.img = this.img - c.img;
        result.real = this.real - c.real;
        return result;
    }
    
    /**
     * Multiplies two complex numbers
     * 
     * @param c - complex number
     * @return - result
     */
    public Complex multiply(Complex c){
        Complex result = new Complex(0,0);
        result.real = (this.real*c.real) - (this.img*c.img);
        result.img = (this.real*c.img) + (this.img*c.real); 
        return result;
    }
    
    /**
     * Calculates the absolute value of the complex number
     * 
     * @return - result
     */
    public double absolute() {
        double result = Math.sqrt((this.real*this.real) + (this.img*this.img));
        return result;
    }
    
    @Override
    public String toString(){
        String s = this.real + " + " + this.img + "i";
        return s;
    }
    
    /**
     * Sorts an array of complex numbers
     * 
     * @param c - array of complex numbers 
     */
    public void sort(Complex[] c){
        Arrays.sort(c);
    }
}
