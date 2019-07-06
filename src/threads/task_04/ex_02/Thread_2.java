package threads.task_04.ex_02;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yuzo
 */
public class Thread_2 implements Runnable {

    Semaphore s;

    public Thread_2(Semaphore s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            s.acquire();

            System.out.println("Phase 2.1...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));

            s.release();
            s.acquire();

            System.out.println("Phase 2.2...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));

            s.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted exception: " + e);
        }
    }
}
