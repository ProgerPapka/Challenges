package task_five.second.concurrent;

import task_five.second.Fork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        //Fully qualified class name - "task_five.second.synchronize.Philosopher" - нарушение JCC
        //Нехватает пробелов после запятых
        Runnable philosopher1 = new task_five.second.synchronize.Philosopher(fork1, fork2,"Mariya");
        Runnable philosopher2 = new task_five.second.synchronize.Philosopher(fork2, fork3,"Natasha");
        Runnable philosopher3 = new task_five.second.synchronize.Philosopher(fork3, fork4,"Vasya");
        Runnable philosopher4 = new task_five.second.synchronize.Philosopher(fork4, fork5,"Petya");
        Runnable philosopher5 = new Philosopher(fork1, fork5,"Alina");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(philosopher1);
        executor.execute(philosopher2);
        executor.execute(philosopher3);
        executor.execute(philosopher4);
        executor.execute(philosopher5);
        executor.shutdown();
    }
}
