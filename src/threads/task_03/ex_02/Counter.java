package threads.task_03.ex_02;

/**
 *
 * @author yuzo Description:
 */
public class Counter {

    private int x = 0;

    public synchronized void increment() {
        x++;
        notifyAll();
    }

    public synchronized void sleepUntil(int sleep) {
        while (x < sleep) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
    }

}
