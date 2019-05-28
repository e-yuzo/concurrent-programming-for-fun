package threads.task_02.ex_01;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadMonitor implements Runnable {
    
    private ThreadGroup group;
    
    public ThreadMonitor(ThreadGroup group) {
        this.group = group;
    }

    @Override
    public void run() {
        Thread[] threadList = new Thread[this.group.activeCount()];
        this.group.enumerate(threadList);
        //System.out.println(this.group.activeCount());
        while (true) {
            for(int i = 0; i < threadList.length; i++) {
                System.out.println("Thread name: " + threadList[i].getName() 
                                   + "-----Thread state: " + threadList[i].getState());
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}