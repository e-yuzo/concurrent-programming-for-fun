/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.wall;

/**
 *
 * @author yuzo
 */
public class Thread_1 implements Runnable {
    
    Barreira b;
    int waiting;

    public Thread_1(Barreira b, int waiting) {
        this.b = b;
        this.waiting = waiting;
    }

    @Override
    public void run() {
        
    }
    
}
