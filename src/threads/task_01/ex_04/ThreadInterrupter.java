package threads.task_01.ex_04;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadInterrupter implements Runnable {

    private final List<Thread> threadList;

    public ThreadInterrupter(List<Thread> threadList) {
        this.threadList = threadList;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000); // interrupt after 3 seconds
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadInterrupter.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.threadList.forEach((Thread thread) -> {
            thread.interrupt();
        });
    }
}
