package task_five.synchronize;

import task_five.synchronize.Bank;
import task_five.synchronize.BankUser;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {

        Bank bank = new Bank(5000);

        Runnable user1 = new BankUser(bank,"Martin");
        Runnable user2 = new BankUser(bank,"Harry");
        Runnable user3 = new BankUser(bank,"Hank");
        Runnable user4 = new BankUser(bank,"Sue");
        Runnable user5 = new BankUser(bank,"Anna");

        Executor executor = Executors.newFixedThreadPool(5);
        executor.execute(user1);
        executor.execute(user2);
        executor.execute(user3);
        executor.execute(user4);
        executor.execute(user5);
    }
}
