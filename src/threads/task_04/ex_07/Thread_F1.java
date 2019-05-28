package threads.task_04.ex_07;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class Thread_F1 implements Runnable {
    
    Queue q;
    
    public Thread_F1(Queue q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            q.F1();
        } catch (Exception ex) {
            Logger.getLogger(Thread_F1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
