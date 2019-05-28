package threads.task_03.ex_02;

/**
 *
 * @author yuzo
 */
public class Sleeper implements Runnable{
    
    Counter c;
    int sleepUntil;
    
    public Sleeper(Counter c, int sleepUntil) {
        this.c = c;
        this.sleepUntil = sleepUntil;
    }

    @Override
    public void run() {
        c.sleepUntil(sleepUntil);
        System.out.println("Finished counter: " + sleepUntil);
    }
}
