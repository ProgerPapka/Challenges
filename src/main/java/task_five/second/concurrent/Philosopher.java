package task_five.second.concurrent;

import task_five.second.Fork;

import java.time.LocalTime;

public class Philosopher implements Runnable{

    private final Fork leftFork;
    private final Fork rightFork;
    private final String name;

    private static LocalTime k = LocalTime.now().plusSeconds(10);
    private int countEat = 0;
    private int countThink = 0;

    public Philosopher(Fork leftFork, Fork rightFork, String name) {
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

    private int period(){
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
