/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.semaphore.rendezvous;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Rendezvous {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(0, true);
        Thread t1 = new Thread(new Thread_1(s));
        Thread t2 = new Thread(new Thread_2(s));
        t1.start();
        t2.start();
    }
}
