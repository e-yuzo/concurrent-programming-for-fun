package threads.interruption.thread_classes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadSleeper implements Runnable {

    @Override
    public void run() {
        long randomSleepTime = (long) (Math.random() * 999 + 1);
        System.out.println(randomSleepTime);
        try {
            Thread.sleep(randomSleepTime);
        } catch (InterruptedException ex) {
            System.out.println("someone interrupted me");
        }

    }
}
