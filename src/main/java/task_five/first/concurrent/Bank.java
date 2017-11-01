package task_five.first.concurrent;

import task_five.first.exception.LackOfMoneyException;

import java.util.concurrent.Semaphore;

public class Bank {

    //В будущем стоит изучить необходимость использования volatile для подобных переменных
    //В реальном продакшене без volatile это не будет работать
    private int moneyAmount;

    //Рекомендуется все final поля (aka. конфигурацию-сервисы) объявлять выше не final,
    //отделяя таким образом поля состояния от полей от конфигурации
    private final Semaphore semaphore;

    public Bank(int moneyAmount) {
        this.moneyAmount = moneyAmount;
        semaphore = new Semaphore(1,true);
    }

    //Этот метод нарушает принцип "Open/Closed" из SOLID
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
