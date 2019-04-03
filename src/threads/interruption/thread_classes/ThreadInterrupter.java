package threads.interruption.thread_classes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadInterrupter implements Runnable {

    private final List<Thread> threadList; // = new ArrayList<String>();

    public ThreadInterrupter(List<Thread> threadList) {
        this.threadList = threadList;
    }

    @Override
    public void run() {
        this.threadList.forEach((Thread thread) -> {
            thread.interrupt();
        });
    }
}
