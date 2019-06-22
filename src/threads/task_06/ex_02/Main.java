/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_06.ex_02;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo Description: Crie uma classe SharedFifoQueue e use Conditions
 * para controlar se a fila est´a vazia ou cheia. Teste usando threads
 * produtoras e consumidoras.
 */
public class Main {

    public static void main(String[] args) {
        SharedFifoQueue sfq = new SharedFifoQueue();
        
        int P = 5;
        int C = 1;
        
        Thread[] prod = new Thread[P];
        for (int i = 0; i < P; i++) {
            prod[i] = new Thread(new Producer(sfq));
            prod[i].setName(Integer.toString(i) + "r");
            prod[i].start();
        }
        Thread[] cons = new Thread[C];
        for (int i = 0; i < C; i++) {
            cons[i] = new Thread(new Consumer(sfq));
            cons[i].setName(Integer.toString(i) + "w");
            cons[i].start();
        }
    }
}

class SharedFifoQueue {

    Lock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    Condition full = lock.newCondition();

    String[] queue = new String[100];
    int in = 0, out = 0, count = 0;

    public void produce(String we) throws Exception {
        lock.lock();
        try {
            while (count == queue.length) {
                full.await();
            }
            queue[in] = we;
            if (++in >= queue.length) {
                in = 0;
            }
            count++;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String consume() throws Exception {
        lock.lock();
        try {
            while (count == 0) {
                empty.await();
            }
            String gime = queue[out];
            if (++out == queue.length) {
                out = 0;
            }
            count--;
            full.signal();
            return gime;
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {

    SharedFifoQueue sfq;

    public Producer(SharedFifoQueue sfq) {
        this.sfq = sfq;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                sfq.produce("Cortex Strikes Back: " + ++i);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1500));
            } catch (Exception ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class Consumer implements Runnable {

    SharedFifoQueue sfq;

    public Consumer(SharedFifoQueue sfq) {
        this.sfq = sfq;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                System.out.println("Crash Bandicoot: " + sfq.consume());
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1500));
            } catch (Exception ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
