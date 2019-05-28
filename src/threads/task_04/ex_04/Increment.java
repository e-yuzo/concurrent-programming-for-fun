package threads.task_04.ex_04;

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
        try {
            c.increment();
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted exception: " + e);
        }
    }
}
