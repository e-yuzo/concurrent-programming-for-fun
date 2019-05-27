package threads.task_01.ex_01;

/**
 *
 * @author yuzo
 */
public class ThreadSleeper implements Runnable {

    @Override
    public void run() {
        long randomSleepTime = (long) (Math.random() * 999 + 1);
        System.out.println(randomSleepTime);
        try {
            Thread.sleep(randomSleepTime);
        } catch (InterruptedException ex) {
            System.out.println("someone interrupted me");
        }

    }
}
