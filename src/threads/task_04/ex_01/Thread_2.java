package threads.task_04.ex_01;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class Thread_2 implements Runnable{
    
    Semaphore s;
    
    public Thread_2(Semaphore s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            s.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("LOL2");
    }
}
