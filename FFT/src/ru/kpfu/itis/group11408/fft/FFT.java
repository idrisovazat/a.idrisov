package ru.kpfu.itis.group11408.fft;


/**
 * Created by Azat on 05.04.2015.
 */
public class FFT {
    Complex[] masA;
    Complex[] masB;
    FFT(double[] a, double[] b){
        int n = a.length >= b.length ? a.length : b.length;
        masA = new Complex[n*2];
        masB = new Complex[n*2];
        for (int i = 0; i < 2*n; i++) {
            if (i >= a.length) masA[i] = new Complex(0,0);
            else masA[i] = new Complex(a[i],0);
        }

        for (int i = 0; i < 2*n; i++) {
            if (i>= b.length) masB[i] = new Complex(0,0);
            else masB[i] = new Complex(b[i], 0);
        }
    }

    public Complex[] start(){
        return multiplay(masA, masB);
    }


    public void fft(Complex[] mas, boolean invert){
        int n = mas.length;
        if (n == 1) return;
        Complex[] a0 = new Complex[n/2];
        Complex[] a1 = new Complex[n/2];

        for (int i=0, j=0; i<n; i+=2, ++j) {
            a0[j] = new Complex(mas[i].getRe(), mas[i].getIm());
            a1[j] = new Complex(mas[i+1].getRe(), mas[i+1].getIm());
        }

        fft(a0, invert);
        fft(a1, invert);

        double ang = 2*Math.PI/n * (invert ? -1 : 1);

        Complex w = new Complex(1,0);
        Complex wn = new Complex(Math.floor(Math.cos(ang)*1000000)/1000000, Math.floor(Math.sin(ang)*1000000)/1000000);

        for (int i=0; i<n/2; i++) {
            Complex temp1 = new Complex(a1[i].getRe(), a1[i].getIm());
            temp1.mult(w);
            Complex temp2 = new Complex(a0[i].getRe(), a0[i].getIm());
            temp2.plus(temp1);
            mas[i] = new Complex(temp2.getRe(), temp2.getIm());
            temp2 = new Complex(a0[i].getRe(), a0[i].getIm());
            temp2.minus(temp1);
            mas[i+n/2] = new Complex(temp2.getRe(), temp2.getIm());
            if (invert) {
                mas[i].delay(2);
                mas[i + n / 2].delay(2);
            }
            w.mult(wn);
        }
    }

    public Complex[] multiplay(Complex a[], Complex b[]) {
        int n = a.length > b.length ? a.length : b.length;
        Complex[] fa = new Complex[n];
        Complex[] fb = new Complex[n];

        for (int i = 0; i < n; i++) {
            if (i > a.length) fa[i] = new Complex(0, 0);
            else fa[i] = new Complex(a[i].getRe(), a[i].getIm());
            if (i > b.length) fb[i] = new Complex(0, 0);
            else fb[i] = new Complex(b[i].getRe(), b[i].getIm());
        }
        fft(fa, false);
        fft(fb, false);
        for (int i = 0; i < n; i++)
            fa[i].mult(fb[i]);

        fft(fa, true);

        for (int i = 0; i < fa.length; i++) {
            fa[i] = new Complex(Math.round(fa[i].getRe() * 2) / 2, Math.round(fa[i].getIm() * 2) / 2);
        }
        return fa;

    }

}
