package threads.task_03.ex_03;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yuzo
 */
public class Increment implements Runnable {

    BoundedCounter bc;

    public Increment(BoundedCounter bc) {
        this.bc = bc;
    }

    @Override
    public void run() {
        while (true) {
            bc.increment();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted exception: " + e);
            }
        }
    }
}
