package ru.kpfu.itis.group11408.fft;

/**
 * Created by Azat on 05.04.2015.
 */
public class Complex {
    double re;
    double im;
    double mas[][];

    Complex(double[] a){
        int n = a.length;
        mas = new double[n][2];
        for (int i = 0; i < n; i++) {
            mas[i][0] = a[i];
            mas[i][1] = 0;
        }
    }

    Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    Complex(){
    }
    public double getRe(){
        return re;
    }

    public double getIm(){
        return im;
    }


    public void w(int n, int k){
        re = Math.cos(2*Math.PI*k/n);
        im = Math.sin(2 * Math.PI * k / n);
    }

    public Complex plus(Complex a, Complex b){
        return new Complex(a.getRe() + b.getRe(), a.getIm() + b.getIm());
    }

    public Complex plus(double re1, double im1, double re2, double im2){
        return new Complex(re1 + re2, im1 + im2);
    }

    public void plus(double re, double im){
        this.re += re;
        this.im += im;
    }
    public void mult(double chislo){
        this.re *= chislo;
        this.im *= chislo;
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
