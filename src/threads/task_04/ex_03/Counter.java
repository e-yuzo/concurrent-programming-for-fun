package threads.task_04.ex_03;

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
