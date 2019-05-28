package threads.task_04.ex_04;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Counter {
    
    Semaphore s; // in Multiplex: Semaphore s = new Semaphore(3)
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
