package task_five.synchronize;

import task_five.exception.LackOfMoneyException;

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
        synchronized (myBank) {
            try {
                while (myBank.hasMoney()) {
                    myBank.getMoney(50);
                }

            } catch (LackOfMoneyException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(name + " took the money." +
                "Thread finished work.");
    }
}
