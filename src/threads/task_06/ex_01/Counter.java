package threads.task_06.ex_01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author yuzo Description: Faça um programa usando Lock para simular a
 * atualização de um contador que é acessado por múltiplas threads. O contador
 * pode diminuir e aumentar.
 */
public class Counter {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        int count = 0;
        
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
