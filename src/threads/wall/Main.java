/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.wall;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Main {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(0);
        Barreira b = new Barreira(s);
        
        Thread t1 = new Thread(new Thread_1(b, 5));
        Thread t2 = new Thread(new Thread_1(b, 1));
        
        t1.start();
        t2.start();
    }
    
}
