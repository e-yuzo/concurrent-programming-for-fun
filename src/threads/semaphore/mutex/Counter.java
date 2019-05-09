/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.semaphore.mutex;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Counter {
    
    Semaphore s;
    int i;
    
    public Counter(Semaphore s) {
        this.s = s;
    }
    
    public synchronized void increment() throws InterruptedException {
        s.acquire();
        System.out.println(++i);
        s.release();
    }
    
}
