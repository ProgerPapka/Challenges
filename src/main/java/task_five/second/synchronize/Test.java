package task_five.second.synchronize;

import task_five.second.Fork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        //Здесь нет необходимости использования класса Fork. Можно использовать просто Object
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        Runnable philosopher1 = new Philosopher(fork1, fork2,"Sara");
        Runnable philosopher2 = new Philosopher(fork2, fork3,"James");
        Runnable philosopher3 = new Philosopher(fork3, fork4,"Amelia");
        Runnable philosopher4 = new Philosopher(fork4, fork5,"Hank");
        Runnable philosopher5 = new Philosopher(fork1, fork5,"Karry");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(philosopher1);
        executor.execute(philosopher2);
        executor.execute(philosopher3);
        executor.execute(philosopher4);
        executor.execute(philosopher5);
        executor.shutdown();
    }
}
