/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.semaphore.barrier;

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
            bar[i] = new Bigger_Wall();
            bar[i].setName(Integer.toString(i));
            bar[i].start();
        }
    }

    static class Wall extends Thread {

        @Override
        public void run() {
            try {
                MUTEX.acquire();
                System.out.println("Thread: " + this.getName() + " reached barrier");
                if (count == N - 1) {// if all thread reaches.
                    LOCK1.release();
                } else {
                    count++;
                }
                MUTEX.release();

                LOCK1.acquire();
                LOCK1.release();
                System.out.println("Thread: " + this.getName() + " ran past barrier");
            } catch (InterruptedException ex) {
            }
        }
    }

    static class Bigger_Wall extends Thread {
        
        @Override
        public void run() {
            for (int i = 0; i < 999; i++) {
                try {
                    MUTEX.acquire();
                    System.out.println("Thread: " + this.getName() + " is working");
                    if (count == N - 1) { // last one to reach barrier
                        LOCK1.release();
                        LOCK2.acquire();
                    } else {
                        count++;
                    }
                    MUTEX.release();
                    
                    LOCK1.acquire();
                    LOCK1.release();
                    System.out.println("Thread: " + this.getName() + " ran past barrier");
                    
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
