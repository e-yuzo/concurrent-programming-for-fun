package threads.task_05.ex_01;

import java.util.concurrent.Semaphore;

// prioritize readers
class ReaderWriterV1 {

    int numReaders = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore wlock = new Semaphore(1); // for everyone

    public void startRead() throws InterruptedException {
        mutex.acquire();
        numReaders++;
        if (numReaders == 1) {
            wlock.acquire();
        }
        mutex.release();
        System.out.println("Reading.");
    }

    public void endRead() throws InterruptedException {
        System.out.println("Finished reading.");
        mutex.acquire();
        numReaders--;
        if (numReaders == 0) {
            wlock.release();
        }
        mutex.release();
    }

    public void startWrite() throws InterruptedException {
        wlock.acquire();
        System.out.println("Writing");
    }

    public void endWrite() {
        wlock.release();
        System.out.println("Finished writing.");
    }
}
