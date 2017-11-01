package task_five.second.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        ReentrantLock fork1 = new ReentrantLock();
        ReentrantLock fork2 = new ReentrantLock();
        ReentrantLock fork3 = new ReentrantLock();
        ReentrantLock fork4 = new ReentrantLock();
        ReentrantLock fork5 = new ReentrantLock();

        Runnable philosopher1 = new Philosopher(fork1, fork2, "Mariya");
        Runnable philosopher2 = new Philosopher(fork2, fork3, "Natasha");
        Runnable philosopher3 = new Philosopher(fork3, fork4, "Vasya");
        Runnable philosopher4 = new Philosopher(fork4, fork5, "Petya");
        Runnable philosopher5 = new Philosopher(fork1, fork5, "Alina");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(philosopher1);
        executor.execute(philosopher2);
        executor.execute(philosopher3);
        executor.execute(philosopher4);
        executor.execute(philosopher5);
        executor.shutdown();
    }
}
