
package threads.interruption.thread_classes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadWatcher implements Runnable {

    private final List<Thread> threadList;

    public ThreadWatcher(List<Thread> threadList) {
        this.threadList = threadList;
    }

    @Override
    public void run() {
        while (true) {
            this.threadList.forEach((Thread thread) -> {
                showThreadStatus(thread);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadInterrupter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    static void showThreadStatus(Thread thread) {
        System.out.println("Name: " + thread.getName() + " State: " + thread.getState() + " Interrupted: " + thread.isInterrupted());
    }
}
