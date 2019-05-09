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
public class Mutex {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1); //quantidade de threads acessando o mesmo recurso ????
        Counter c = new Counter(s);
        
        Thread t1 = new Thread(new Increment(c));
        Thread t2 = new Thread(new Increment(c));
        t1.start();
        t2.start();
    }
    
}
