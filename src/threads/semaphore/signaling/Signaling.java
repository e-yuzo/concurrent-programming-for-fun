/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.semaphore.signaling;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 * Release is necessary, so permits turns 1, so t2 can acquire.
 */
public class Signaling {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(0);
        Thread t1 = new Thread(new Thread_1(s));
        Thread t2 = new Thread(new Thread_2(s));
        t1.start();
        t2.start();
    }
    
}
