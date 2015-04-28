package ru.kpfu.itis.group11408.fft;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        start(64);
        System.out.println("-----------------------------");
        start(8192);
        System.out.println("-----------------------------");
        start(8192 * 64);
    }
    public static void start(int n){
        try {
            double[] a = new double[n];
            double[] b = new double[n];

            for (int i = 0; i < n; i++) {
                a[i] = Math.floor(Math.random() * 10);
                b[i] = Math.floor(Math.random() * 10);
            }
            long start = System.currentTimeMillis();
            FFT f = new FFT(a, b);
            Complex[] mas = f.start();
            for (int i = 0; i < mas.length; i++) {
// System.out.print(mas[i].getRe() + "+");
            }
            long end = System.currentTimeMillis();
            System.out.println();
            System.out.println("Время работы методом Фурье при n=" + n + ": " + countTime(start, end) + " секунд");

            start = System.currentTimeMillis();
            Object[] c = multiply(a, b);
            end = System.currentTimeMillis();
            System.out.println("Время работы простым методом при n=" + n + ": " + countTime(start, end) + " секунд");
        }catch(Exception e){ e.printStackTrace();}
    }
    private static Object[] multiply(double[] list1, double[] list2) {
        ArrayList<Double> array =new ArrayList<Double>(list1.length+list2.length);

        for (int i = 0 ; i < list1.length + list2.length ; i++)
            array.add((double) 0);
        for (int i = 0; i < list1.length; i++)
            for (int j = 0; j < list2.length; j++)
                array.set(i+j, ((list1[i] * list2[j])+ array.get(i + j)));

        return array.toArray();

    }
    public static double countTime(long start, long end){
        long delta = end - start;
        return delta/1000.0;
    }
}