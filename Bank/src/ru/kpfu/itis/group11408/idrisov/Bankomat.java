package ru.kpfu.itis.group11408.idrisov;


/**
 * Created by Татагромодуль on 12.05.2015.
*/
public class Bankomat implements Runnable {
    private int money;
    private int id;

    public Bankomat(int id, int money){
        this.id = id;
        this.money = money;
    }

    public void work() {
       //for (int i = 0; i < 10; i++) {
            int check = (int)(Math.random() * 2);
            int sum = ((int)(Math.random() * 1000));
            int newID = ((int)(Math.random() * 100));
            if (check == 0) {
                System.out.println("Клиент №" + newID + "зачислил " + sum + "рублей. Банкомат №" + this.id);
            Client client = new Client(newID, (int)(Math.random() * 100000));
                client.setMoney(sum);
            } else {
                System.out.println("Клиент №" + newID + "снял " + sum + "рублей. Банкомат №" + this.id);
                Client client = new Client(newID, (int)(Math.random() * 100000));
                client.getMoney(sum);
            }

    }

    public int getMoney() {
        return money;
    }

    public void putMoney(int money, Client client) {
      // Client.setMoney(this.money);
    }

    @Override
    public void run() {
        this.work();
    }
}