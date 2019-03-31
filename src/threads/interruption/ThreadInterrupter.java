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
 * @author yuzo
 * Description: interrupt ThreadReader and ThreadSleeper threads.
 */
public class ThreadInterrupter implements Runnable {

    private final List<Thread> threadList; // = new ArrayList<String>();

    public ThreadInterrupter(List<Thread> threadList) {
        this.threadList = threadList;
    }

    @Override
    public void run() {
        while (true) {
            this.threadList.forEach((Thread thread) -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadInterrupter.class.getName()).log(Level.SEVERE, null, ex);
                }
                thread.interrupt();
            });
        }
    }
}
