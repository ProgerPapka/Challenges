package task_five.first.concurrent;

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
        try {
            myBank.getSemaphore().acquire();
            while (myBank.hasMoney()) {
                myBank.getMoney(50);
            }
            myBank.getSemaphore().release();
        } catch (LackOfMoneyException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + " took the money." +
                "Thread finished work.");
    }
}
