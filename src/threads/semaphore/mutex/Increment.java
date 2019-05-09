/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.semaphore.mutex;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yuzo
 */
public class Increment implements Runnable {
    
    Counter c;
    
    public Increment(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        try {
            c.increment();
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted exception: " + e);
        }
    }
    
    
    
}
