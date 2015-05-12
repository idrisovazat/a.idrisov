package ru.kpfu.itis.group11408.idrisov;

/**
 * Created by Татагромодуль on 12.05.2015.
*/
public class Client {

    private long ID;
    private int balance;

   public Client(long ID, int balance) {
        this.ID = ID;
        this.balance = balance;
    }
    

    public long getID(Client client){
        return this.ID;
    }

    public void getMoney(int money){
        this.balance -= money;
    }


    public int getBalance(){
        return this.balance;
    }


    public void setMoney(int money){
        this.balance += money;
    }
}