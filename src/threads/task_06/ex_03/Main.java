/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_06.ex_03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuzo Description: Fa¸ca uma classe ArrayListThreadSafe usando
 * ReadWriteLock. Teste usando threads que realizam leitura e escrita para essa
 * estrutura.
 */
public class Main {

    public static void main(String[] args) {
        ArrayListThreadSafe alts = new ArrayListThreadSafe();
        
        int W = 2;
        int R = 2;
        
        Thread[] writers = new Thread[W];
        for (int i = 0; i < W; i++) {
            writers[i] = new Thread(new Writer(alts));
            writers[i].setName(Integer.toString(i) + "r");
            writers[i].start();
        }
        Thread[] readers = new Thread[R];
        for (int i = 0; i < R; i++) {
            readers[i] = new Thread(new Reader(alts));
            readers[i].setName(Integer.toString(i) + "w");
            readers[i].start();
        }
    }

}

class ArrayListThreadSafe {

    List<String> list = new ArrayList<>();
    ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
    Lock r = rrwl.readLock();
    Lock w = rrwl.writeLock();
    int records = 0;

    public void write(String we) {
        w.lock();
        try {
            list.add(we);
            records++;
        } finally {
            w.unlock();
        }
    }

    public String read(int index) {
        r.lock();
        try {
            if (index < records) {
                return list.get(index);
            }
            return null;
        } finally {
            r.unlock();
        }
    }
}

class Writer implements Runnable {

    ArrayListThreadSafe alts;

    public Writer(ArrayListThreadSafe alts) {
        this.alts = alts;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                alts.write("Cortex Strikes Back: " + ++i);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1500));
            } catch (Exception ex) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class Reader implements Runnable {

    ArrayListThreadSafe alts;

    public Reader(ArrayListThreadSafe alts) {
        this.alts = alts;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                String read;
                if ((read = alts.read(i)) != null) {
                    System.out.println("Crash Bandicoot: " + read);
                    i++;
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1500));
            } catch (Exception ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
