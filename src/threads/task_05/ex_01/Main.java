package threads.task_05.ex_01;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo Description: Implementar soluções para o problema dos
 * leitores-escritores que: 1.1 priorize os leitores. 1.2 sem starvation. 1.3
 * priorize os escritores.
 */
public class Main {

    public static void main(String[] args) {
        int r = 5;
        int w = 5;

        //change the next line to 'V2' or 'V3'
        ReadWriter rw = new ReaderWriterV3();

        Thread[] reader = new Thread[r];
        for (int i = 0; i < r; i++) {
            reader[i] = new Thread(new Reader(rw));
            reader[i].setName(Integer.toString(i) + "r");
            reader[i].start();
        }
        Thread[] writer = new Thread[w];
        for (int i = 0; i < r; i++) {
            writer[i] = new Thread(new Writer(rw));
            writer[i].setName(Integer.toString(i) + "w");
            writer[i].start();
        }
    }
}

class Reader implements Runnable {

    ReadWriter rw;

    public Reader(ReadWriter rw) {
        this.rw = rw;
    }

    @Override
    public void run() {
        while (true) {
            try {
                rw.startRead();
                rw.endRead();
            } catch (InterruptedException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class Writer implements Runnable {

    ReadWriter rw;

    public Writer(ReadWriter rw) {
        this.rw = rw;
    }

    @Override
    public void run() {
        while (true) {
            try {
                rw.startWrite();
                rw.endWrite();
            } catch (InterruptedException ex) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
