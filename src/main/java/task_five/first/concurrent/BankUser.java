package task_five.first.concurrent;

import task_five.first.exception.LackOfMoneyException;

public class BankUser implements Runnable {

    private final Bank myBank;
    private final String name;


    public BankUser(Bank myBank, String name) {
        this.myBank = myBank;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                myBank.acquireSemaphoreBank();
                if (myBank.hasMoney()) {
                    myBank.getMoney(50);
                } else {
                    break;
                }
                myBank.releaseSemaphoreBank();
            }
        } catch (LackOfMoneyException | InterruptedException e) {
            myBank.releaseSemaphoreBank();
            System.out.println(e.getMessage());
        } finally {
            myBank.releaseSemaphoreBank();
        }
        System.out.println(name + " took the money." +
                "Thread finished work.");
    }
}
