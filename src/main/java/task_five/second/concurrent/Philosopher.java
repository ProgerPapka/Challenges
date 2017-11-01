package task_five.second.concurrent;

import java.time.LocalTime;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {

    private static LocalTime k = LocalTime.now().plusSeconds(10);

    private final ReentrantLock leftFork;
    private final ReentrantLock rightFork;
    private final String name;

    private int countEat = 0;
    private int countThink = 0;

    public Philosopher(ReentrantLock leftFork, ReentrantLock rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
    }

    private void eating() {
        System.out.println(name + " eat:" + (++countEat));
    }

    private void thinking() throws InterruptedException {
        System.out.println(name + " think:" + (++countThink));
        Thread.sleep(100);
    }

    private int period() {
        return k.getSecond() - LocalTime.now().getSecond();
    }

    @Override
    public void run() {
        try {
            while (period() != 0) {
                leftFork.lock();
                rightFork.lock();
                eating();
                leftFork.unlock();
                rightFork.unlock();
                thinking();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
