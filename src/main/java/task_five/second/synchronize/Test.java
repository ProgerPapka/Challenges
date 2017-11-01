package task_five.second.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        Object fork1 = new Object();
        Object fork2 = new Object();
        Object fork3 = new Object();
        Object fork4 = new Object();
        Object fork5 = new Object();

        Runnable philosopher1 = new Philosopher(fork1, fork2, "Sara");
        Runnable philosopher2 = new Philosopher(fork2, fork3, "James");
        Runnable philosopher3 = new Philosopher(fork3, fork4, "Amelia");
        Runnable philosopher4 = new Philosopher(fork4, fork5, "Hank");
        Runnable philosopher5 = new Philosopher(fork1, fork5, "Karry");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(philosopher1);
        executor.execute(philosopher2);
        executor.execute(philosopher3);
        executor.execute(philosopher4);
        executor.execute(philosopher5);
        executor.shutdown();
    }
}
