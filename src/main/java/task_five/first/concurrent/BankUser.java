package task_five.first.concurrent;

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
                myBank.getSemaphore().acquire();
                if(myBank.hasMoney()) {
                    myBank.getMoney(50);
                } else {
                    myBank.getSemaphore().release();  //обычно такие операции, как release делают в секции finally, чтобы гарантированно отпускать
                    break;
                }
                myBank.getSemaphore().release();
            }
        } catch (LackOfMoneyException | InterruptedException e) {
            myBank.getSemaphore().release();
            System.out.println(e.getMessage());
        }
        System.out.println(name + " took the money." +
                "Thread finished work.");
    }
}
