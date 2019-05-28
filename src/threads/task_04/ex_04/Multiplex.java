package threads.task_04.ex_04;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Multiplex {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(3); //three threads can access
        Counter c = new Counter(s);
        
        Thread t1 = new Thread(new Increment(c));
        Thread t2 = new Thread(new Increment(c));
        Thread t3 = new Thread(new Increment(c));
        t1.start();
        t2.start();
        t3.start();
    }
}
