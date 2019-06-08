package threads.task_05.ex_01;

import java.util.concurrent.Semaphore;

// prioritize writers
class ReaderWriterV2 implements ReadWriter{

    int numReaders = 0;
    int numWriters = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore rlock = new Semaphore(1); // for everyone
    Semaphore wlock = new Semaphore(1); // for everyone

    @Override
    public void startRead() throws InterruptedException {
        rlock.acquire(); // see if this is correct
        System.out.println("Reading.");
    }

    @Override
    public void endRead() throws InterruptedException {
        rlock.release();
        System.out.println("Finished reading.");
    }

    @Override
    public void startWrite() throws InterruptedException {
        mutex.acquire(); // stop here
        numWriters++;
        if (numWriters == 1) { // this is for rlock acquire only
            rlock.acquire(); // acquire this
        }
        mutex.release(); // let others
        wlock.acquire();
        System.out.println("Writing.");
    }

    @Override
    public void endWrite() throws InterruptedException {
        wlock.release();
        System.out.println("Finished writing.");
        mutex.acquire();
        numWriters--;
        if (numWriters == 0) {
            rlock.release();
        }
        mutex.release();
    }

//    public void startRead() throws InterruptedException {
//        mutex.acquire();
//        numReaders++;
//        if (numReaders == 1) {
//            wlock.acquire();
//        }
//        mutex.release();
//        System.out.println("Reading");
//    }
//    public void endRead() throws InterruptedException {
//        mutex.acquire();
//        numReaders--;
//        if (numReaders == 0) {
//            wlock.release(); // if there are more readers, dont release
//        }
//        mutex.release();
//        System.out.println("Finished reading.");
//    }
//    public void startWrite() throws InterruptedException {
//        wlock.acquire();
//        System.out.println("Writing");
//    }
//    public void endWrite() {
//        wlock.release();
//        System.out.println("Finished writing.");
//    }
}
