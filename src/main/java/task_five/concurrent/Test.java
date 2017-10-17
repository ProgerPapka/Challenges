package task_five.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {

        Bank bank = new Bank(5000);

        Runnable user1 = new BankUser(bank,"Ramilya");
        Runnable user2 = new BankUser(bank,"Aliya");
        Runnable user3 = new BankUser(bank,"Guzel");
        Runnable user4 = new BankUser(bank,"Airat");
        Runnable user5 = new BankUser(bank,"Radik");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(user1);
        executor.execute(user2);
        executor.execute(user3);
        executor.execute(user4);
        executor.execute(user5);
        executor.shutdown();
    }
}
