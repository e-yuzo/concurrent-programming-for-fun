package threads.task_01.ex_03;

import java.util.List;

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
