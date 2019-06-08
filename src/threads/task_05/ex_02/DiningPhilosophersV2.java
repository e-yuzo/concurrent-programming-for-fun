package threads.task_05.ex_02;

/**
 * Philosopher picks both or none
 *
 * @author yuzo
 */
import java.util.concurrent.Semaphore;

public class DiningPhilosophersV2 implements Thinker {

    int n = 0;
    Semaphore[] fork = null;

    public DiningPhilosophersV2(int initN) {
        n = initN;
        fork = new Semaphore[n];
        for (int i = 0; i < n; i++) {
            fork[i] = new Semaphore(1);
        }
    }

    @Override
    public void pickup(int i) throws Exception {
        boolean both = false;
        while (!both) {
            fork[i].acquire();
            if (!fork[(i + 1) % n].tryAcquire()) {
                fork[i].release();
            } else {
                both = true;
            }
        }
    }

    @Override
    public void drop(int i) throws Exception {
        fork[i].release();
        fork[(i + 1) % n].release();
    }
}
