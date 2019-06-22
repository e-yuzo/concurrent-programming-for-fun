package threads.task_06.ex_01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author yuzo Description: Faça um programa usando Lock para simular a
 * atualização de um contador que é acessado por múltiplas threads. O contador
 * pode diminuir e aumentar.
 */
public class Main {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        int count = 0;
        CounterT ct = new CounterT(count, lock);
        
        int I = 5;
        int D = 5;
        
        Thread[] inc = new Thread[I];
        for (int i = 0; i < I; i++) {
            inc[i] = new Thread(new Incrementer(ct));
            inc[i].setName(Integer.toString(i) + "r");
            inc[i].start();
        }
        Thread[] dec = new Thread[D];
        for (int i = 0; i < D; i++) {
            dec[i] = new Thread(new Decrementer(ct));
            dec[i].setName(Integer.toString(i) + "w");
            dec[i].start();
        }
    }
}

class CounterT {

    int count;
    Lock lock;

    public CounterT(int count, Lock lock) {
        this.count = count;
        this.lock = lock;
    }

    public void decrement() {
        lock.lock();
        try {
            count++;
            System.out.println(count);
        } finally {
            lock.unlock();
        }
    }

    public void increment() {
        lock.lock();
        try {
            count--;
            System.out.println(count);
        } finally {
            lock.unlock();
        }
    }
}

class Incrementer implements Runnable {

    CounterT ct;

    public Incrementer(CounterT ct) {
        this.ct = ct;
    }

    @Override
    public void run() {
        while (true) {
            ct.increment();
        }
    }

}

class Decrementer implements Runnable {

    CounterT ct;

    public Decrementer(CounterT ct) {
        this.ct = ct;
    }

    @Override
    public void run() {
        while (true) {
            ct.decrement();
        }
    }
}
