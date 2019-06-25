package threads.task_05.ex_02;

/**
 * One philosopher is left out in each round.
 *
 * @author yuzo
 */
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophersV3 implements Thinker {

    int n = 0;
    Semaphore[] fork = null;
    boolean[] round;
    int leftOut = 0;
    ReentrantLock lock = new ReentrantLock();

    public DiningPhilosophersV3(int initN) {
        n = initN;
        round = new boolean[initN];
        fork = new Semaphore[n];
        for (int i = 0; i < n; i++) {
            fork[i] = new Semaphore(1);
        }
    }

    @Override
    public void pickup(int i) throws Exception {
        if (i != leftOut % n) {
            fork[i].acquire();
            fork[(i + 1) % n].acquire();
            lock.lock();
            try {
                System.out.println("Left out: " + leftOut);
                round[i] = true;
                if (simpleCountHeHe() == n - 1) {
                    leftOut++;
                    round = new boolean[n];
                }
            } finally {
                lock.unlock();
            }
        }
    }
    
    public int simpleCountHeHe() {
        int counter = 0;
        for (int i = 0; i < round.length; i++) {
            if (round[i] == true)
                counter++;
        }
        return counter;
    }

    @Override
    public void drop(int i) throws Exception {
        fork[i].release();
        fork[(i + 1) % n].release();
    }
}
