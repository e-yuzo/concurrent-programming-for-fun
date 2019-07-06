package threads.task_04.ex_01;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yuzo
 */
public class Thread_1 implements Runnable {

    Semaphore s;

    public Thread_1(Semaphore s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 4000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted exception: " + e);
        }
        s.release();
    }
}
