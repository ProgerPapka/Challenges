package task_five.first.concurrent;

import task_five.first.exception.LackOfMoneyException;

import java.util.concurrent.Semaphore;

public class Bank {

    private final Semaphore semaphore;

    private volatile int moneyAmount;

    public Bank(int moneyAmount) {
        this.moneyAmount = moneyAmount;
        semaphore = new Semaphore(1, true);
    }

    public void acquireSemaphoreBank() throws InterruptedException {
        semaphore.acquire();
    }

    public void releaseSemaphoreBank() {
        semaphore.release();
    }

    public void getMoney(int amount) throws LackOfMoneyException {
        if (moneyAmount < amount)
            throw new LackOfMoneyException("Bank does not have that kind of money");
        moneyAmount -= amount;
        System.out.println("Bank has " + moneyAmount + " money.");
    }

    public boolean hasMoney() {
        return moneyAmount > 0;
    }
}
