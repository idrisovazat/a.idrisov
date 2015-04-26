package ru.kpfu.itis.group11408.fft;

/**
 * Created by Татагромодуль on 27.04.2015.
 */
public class Main {
    public static void main(String[] args) {
        int n = 8;
        double[] a = new double[n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = Math.floor(Math.random()*10);
            System.out.print(a[i] + "  a ");
            b[i] = Math.floor(Math.random()*10);
            System.out.println(b[i] + " b ");
        }
        FFT f = new FFT(a,b);
        Complex[] mas = f.start();
        for (int i = 0; i < mas.length; i++) {
            System.out.println(mas[i].getRe() + " +i" + mas[i].getIm());
        }
    }
}
