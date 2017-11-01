package task_five.first.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {

        Bank bank = new Bank(5000);

        Runnable user1 = new BankUser(bank, "Martin");
        Runnable user2 = new BankUser(bank, "Harry");
        Runnable user3 = new BankUser(bank, "Hank");
        Runnable user4 = new BankUser(bank, "Sue");
        Runnable user5 = new BankUser(bank, "Anna");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(user1);
        executor.execute(user2);
        executor.execute(user3);
        executor.execute(user4);
        executor.execute(user5);
        executor.shutdown();
    }
}
