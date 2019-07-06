package threads.task_01.ex_04;

import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadWatcher implements Runnable {

    private final List<Thread> threadList;
    private List<Boolean> threadListState;

    public ThreadWatcher(List<Thread> threadList) {
        this.threadList = threadList;
        threadListState = new ArrayList<>(Arrays.asList(new Boolean[threadList.size()]));
        Collections.fill(threadListState, Boolean.FALSE);
    }

    @Override
    public void run() {
        while (true) {
            for (int index = 0; index < threadList.size(); index++){
                showThreadStatus(threadList.get(index), index);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadInterrupter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void showThreadStatus(Thread thread, int index) {
        boolean isInterr = thread.isInterrupted();
        if (threadListState.get(index) != isInterr) {
            threadListState.set(index, isInterr);
            System.out.println("Thread " + thread.getName() + " changed state to " + thread.getState() + ".");
        }
    }
}
