package ru.kpfu.itis.group11408.idrisov;

/**
 * Created by Татагромодуль on 12.05.2015.
*/

public class Bank {
    private long money = 100000000000L;


    void putMoney(int money) {
        this.money += money;
    }

    void addClient(long ID, int balance){
        new Client(ID, balance);
    }
}
