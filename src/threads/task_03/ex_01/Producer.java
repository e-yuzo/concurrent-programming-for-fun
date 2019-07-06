package threads.task_03.ex_01;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yuzo
 */
public class Producer implements Runnable {

    private int deathCount = 0;
    NewsPool newsPool;

    public Producer(NewsPool newsPool) {
        this.newsPool = newsPool;
    }

    @Override
    public void run() {
        while (true) {
            deathCount++;
            newsPool.addNews("You died " + deathCount + " times in Crash Bandicoot.");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted exception: " + e);
            }
        }
    }
    
}