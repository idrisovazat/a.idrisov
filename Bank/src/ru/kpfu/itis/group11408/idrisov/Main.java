package ru.kpfu.itis.group11408.idrisov;

/**
 * Created by Татагромодуль on 12.05.2015.
 */
public class Main {




    public static void main(String[] args) throws InterruptedException {

        Client Fedor = new Client(0L, 100000);
        Client Ambrosij = new Client(1L, 25000);
        Client Serhio = new Client(2L, 36000);
        Client Bunisio = new Client(3L, 36000);
        Client Arhan = new Client(4L, 36000);
        Client Mazhor = new Client(5L, 36000);

        Bankomat bankomat1 = new Bankomat(895, 150000);
        Bankomat bankomat2 = new Bankomat(478, 50000);
        Bankomat bankomat3 = new Bankomat(101, 2550000);

        for (int i = 0; i < 10; i++) {
            Thread one = new Thread(bankomat1);
            Thread two = new Thread(bankomat2);
            Thread three = new Thread(bankomat3);
            one.start();
            Thread.sleep(1000);
            two.start();
            Thread.sleep(1000);
            three.start();

            Thread.sleep(1000);
        }

        //while (true)
       // System.out.println("Главный поток");
    }

}
