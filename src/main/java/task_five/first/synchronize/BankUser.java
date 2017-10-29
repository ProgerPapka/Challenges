package task_five.first.synchronize;

import task_five.first.exception.LackOfMoneyException;

public class BankUser implements Runnable {

    private final Bank myBank;
    private String name;    //почему это поле не final

    //Этот конструктор не используется, значит может быть удалён
    public BankUser(Bank myBank) {
        this.myBank = myBank;
    }

    public BankUser(Bank myBank, String name) {
        this.myBank = myBank;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (myBank) {
                    if (myBank.hasMoney()) {
                        System.out.println("Thread " + Thread.currentThread().getName() +
                                " want get 50 money.");
                        myBank.getMoney(50);
                    } else {
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (LackOfMoneyException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + " took the money." +
                "Thread finished work.");
    }
}
