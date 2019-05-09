/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.semaphore.signaling;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class Thread_2 implements Runnable{
    
    Semaphore s;
    
    public Thread_2(Semaphore s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            s.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("LOL2");
    }
}
