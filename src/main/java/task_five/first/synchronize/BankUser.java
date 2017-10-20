package task_five.first.synchronize;

import task_five.first.exception.LackOfMoneyException;

public class BankUser implements Runnable {

    private final Bank myBank;
    private String name;

    public BankUser(Bank myBank) {
        this.myBank = myBank;
    }

    public BankUser(Bank myBank, String name) {
        this.myBank = myBank;
        this.name = name;
    }

    @Override
    public void run() {
            while (true) {
                synchronized (myBank) {
                    try {
                        if (myBank.hasMoney()) {
                            System.out.println("Thread " + Thread.currentThread().getName() +
                            " want get 50 money.");
                            myBank.getMoney(50);
                        } else {
                            break;
                        }
                    } catch (LackOfMoneyException e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        System.out.println(name + " took the money." +
                "Thread finished work.");
    }
}
