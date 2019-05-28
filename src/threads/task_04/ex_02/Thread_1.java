package threads.task_04.ex_02;

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
            
            System.out.println("Phase 1.1...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));

            s.release();
            s.acquire();
            
            System.out.println("Phase 1.2...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
            
            s.release();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted exception: " + e);
        }
    }
}
