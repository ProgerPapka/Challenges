package task_five.concurrent;

import task_five.exception.LackOfMoneyException;

import java.util.concurrent.Semaphore;

public class Bank {

    private int moneyAmount;
    private final Semaphore semaphore;

    public Bank(int moneyAmount) {
        this.moneyAmount = moneyAmount;
        semaphore = new Semaphore(1,true);
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void getMoney(int amount) throws LackOfMoneyException {
        if(moneyAmount < amount)
            throw new LackOfMoneyException("Bank does not have that kind of money");
        moneyAmount -= amount;
        System.out.println("Bank has " + moneyAmount + " money.");
    }

    public boolean hasMoney(){
        return moneyAmount > 0;
    }
}
