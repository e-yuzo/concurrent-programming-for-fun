package threads.task_04.ex_06;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 * Description: Threds em um laço executam uma série de passos e sincronizam em
 * uma barreira a cada passo. Faça um código que implemente uma barreira
 * reusável que feche a si própria após todas as threads passarem.
 */
public class Main {

    private static final Semaphore MUTEX = new Semaphore(1);
    private static final Semaphore LOCK1 = new Semaphore(0);
    private static final Semaphore LOCK2 = new Semaphore(1);

    private static int count = 0;
    private static final int N = 5;

    public static void main(String[] args) {
        Thread[] bar = new Thread[N];
        for (int i = 0; i < N; i++) {
            bar[i] = new Thread(new Bigger_Wall());
            bar[i].setName(Integer.toString(i));
            bar[i].start();
        }
    }

    static class Bigger_Wall implements Runnable {
        
        @Override
        public void run() {
            for (int i = 0; i < 999; i++) {
                try {
                    MUTEX.acquire();
                    System.out.println("Thread: " + Thread.currentThread().getName() + " is working");
                    if (count == N - 1) { // last one to reach barrier
                        LOCK1.release();
                        LOCK2.acquire();
                    } else {
                        count++;
                    }
                    MUTEX.release();
                    
                    LOCK1.acquire();
                    LOCK1.release();
                    System.out.println("Thread: " + Thread.currentThread().getName() + " ran past barrier");
                    
                    MUTEX.acquire();
                    if (count == 0) { // reset everything
                        LOCK1.acquire();
                        LOCK2.release();
                    } else {
                        count--;
                    }
                    MUTEX.release();
                    
                    LOCK2.acquire();
                    LOCK2.release();
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
