package ru.kpfu.itis.group11408.fft;

/**
 * Created by Azat on 05.04.2015.
 */
public class Complex {
    double re;
    double im;

    Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    public double getRe(){
        return re;
    }
    public double getIm(){
        return im;
    }

    public void mult(Complex b){
        double thisRe = this.re;
        double thisIm = this.im;
        this.re = thisRe * b.re - thisIm * b.im;
        this.im = thisRe * b.im + thisIm * b.re;
    }

    public void plus(Complex b){
        this.re += b.re;
        this.im += b.im;
    }
    public void minus(Complex b){
        this.re -= b.re;
        this.im -= b.im;
    }
    public void delay(double n) {
        this.re /= n;
        this.im /= n;
    }

}