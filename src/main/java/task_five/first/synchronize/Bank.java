package task_five.first.synchronize;

import task_five.first.exception.LackOfMoneyException;

public class Bank {

    private volatile int moneyAmount;

    public Bank(int moneyAmount) {
        this.moneyAmount = moneyAmount;
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
