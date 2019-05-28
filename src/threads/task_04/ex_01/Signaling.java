package threads.task_04.ex_01;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 * Release is necessary: permits turns 1, so that t2 can acquire.
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
