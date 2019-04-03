/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.state.thread_classes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadDoesNothing implements Runnable {

    @Override
    public void run() {
        
        //System.out.println(tg.getName());
        while (true) {
            System.out.println("we just wait forever");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadDoesNothing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
