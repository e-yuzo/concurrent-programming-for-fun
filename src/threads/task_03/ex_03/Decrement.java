package threads.task_03.ex_03;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yuzo
 */
public class Decrement implements Runnable {

    BoundedCounter bc;

    public Decrement(BoundedCounter bc) {
        this.bc = bc;
    }

    @Override
    public void run() {
        while (true) {
            bc.decrement();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted exception: " + e);
            }
        }
    }
}
