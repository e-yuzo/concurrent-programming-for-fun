package threads.task_04.ex_05;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Barrier {

    private static final Semaphore MUTEX = new Semaphore(1);
    private static final Semaphore LOCK1 = new Semaphore(0);
    private static final Semaphore LOCK2 = new Semaphore(1);

    private static int count = 0;
    private static final int N = 5;

    public static void main(String[] args) {
        Thread[] bar = new Thread[N];
        for (int i = 0; i < N; i++) {
            bar[i] = new Thread(new Wall());
            bar[i].setName(Integer.toString(i));
            bar[i].start();
        }
    }

    static class Wall implements Runnable {

        @Override
        public void run() {
            try {
                MUTEX.acquire();
                System.out.println("Thread: " + Thread.currentThread().getName() + " reached barrier");
                if (count == N - 1) {// if all thread reaches.
                    LOCK1.release();
                } else {
                    count++;
                }
                MUTEX.release();

                LOCK1.acquire();
                LOCK1.release();
                System.out.println("Thread: " + Thread.currentThread().getName() + " ran past barrier");
            } catch (InterruptedException ex) {
            }
        }
    }
}
