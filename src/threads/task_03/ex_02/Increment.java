package threads.task_03.ex_02;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yuzo
 */
public class Increment implements Runnable {

    Counter c;

    public Increment(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            c.increment();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted exception: " + e);
            }
        }
    }
}
