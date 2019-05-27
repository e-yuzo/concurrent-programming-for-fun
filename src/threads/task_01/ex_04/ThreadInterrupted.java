package threads.task_01.ex_04;

/**
 *
 * @author yuzo
 */
public class ThreadInterrupted implements Runnable{
    
    private Boolean interrupted = false;

    @Override
    public void run() {
        while (!this.interrupted) {
            
        }
    }
}
