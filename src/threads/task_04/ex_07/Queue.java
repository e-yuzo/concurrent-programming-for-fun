package threads.task_04.ex_07;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Queue {
    
    Semaphore mutex = new Semaphore(1);
    Semaphore f1 = new Semaphore(0);
    Semaphore f2 = new Semaphore(0);
    Semaphore sync = new Semaphore(0);
    int queue1 = 0;
    int queue2 = 0;

    public synchronized void F1() throws Exception {
        mutex.acquire();
        if (queue2 > 0) { // there is a thread in queue2
            queue2--;
            f1.release();
        } else {
            queue1++; // queue = 1
            mutex.release();
            f2.acquire(); // stop here
        }
        System.out.println("f1");
        sync.acquire(); // sincronização
        mutex.release(); //alguém precisa fazer o release
    }

    public synchronized void F2() throws Exception {
        mutex.acquire();
        if (queue1 > 0) {
            queue1--;
            f2.release(); // quer dizer que o f1 está esperando
        } else {
            queue2++;
            mutex.release();
            f1.acquire();
        }
        System.out.println("f2");
        sync.release();
    }
}
