/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo Description: watch thread state in a 5 second interval.
 */
public class ThreadWatcher implements Runnable {

    private final List<Thread> threadList; // = new ArrayList<String>();

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
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadInterrupter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    static void showThreadStatus(Thread thread) {
        System.out.println(thread.getName() + " State: " + thread.getState());
    }
}
