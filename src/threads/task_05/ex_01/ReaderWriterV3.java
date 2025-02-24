package threads.task_05.ex_01;

import java.util.concurrent.Semaphore;

// no starvation
class ReaderWriterV3 implements ReadWriter {

    int numReaders = 0;
    int numWriters = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore resource = new Semaphore(1); // for everyone

    @Override
    public void startRead() throws InterruptedException {
        mutex.acquire();
        // if writers are trying to access the resource, we need to secure resource again
        if (numWriters > 0 || numReaders == 0) { // initially numReaders will be 0
            mutex.release();
            resource.acquire(); //if numWriters > 0 then stop incrementing numReaders. just wait for the resource
            mutex.acquire();
        }
        numReaders++; // if numReaders is 1, don't need to secure resource
        mutex.release();
        System.out.println("Reading.");
    }

    @Override
    public void endRead() throws InterruptedException {
        mutex.acquire();
        numReaders--;
        if (numReaders == 0) {
            resource.release();
        }
        mutex.release();
        System.out.println("Finished reading.");
    }

    @Override
    public void startWrite() throws InterruptedException {
        mutex.acquire();
        numWriters++;
        mutex.release();
        
        resource.acquire();
        System.out.println("Writing.");
    }

    @Override
    public void endWrite() throws InterruptedException {
        mutex.acquire();
        numWriters--;
        mutex.release();
        
        resource.release();
        System.out.println("Finished writing.");
    }
}
