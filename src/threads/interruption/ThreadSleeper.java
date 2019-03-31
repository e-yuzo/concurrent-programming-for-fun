/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo 
 * Description: programa modificado para que as threads não terminem.
 */
public class ThreadSleeper implements Runnable {

    @Override
    public void run() {
        while (true) {
            long randomSleepTime = (long) (Math.random() * 999 + 1);
            System.out.println(randomSleepTime);
            try {
                Thread.sleep(randomSleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadSleeper.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Thread.interrupted()) {
                System.out.println("ThreadReader interrupted.");
            }
        }
    }
}
