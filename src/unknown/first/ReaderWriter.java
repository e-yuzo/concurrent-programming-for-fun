
package unknown.first;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class ReaderWriter {
    
    int numReaders = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore wLock = new Semaphore(1);
    
    public void startRead() throws InterruptedException {
        mutex.acquire();
        numReaders++;
        if (numReaders == 1) {
            wLock.acquire(); //verify if anyone is writing
        }
        mutex.release();
    }
    
    public void endRead() throws InterruptedException {
        mutex.acquire();
        numReaders--;
        if (numReaders == 0) {
            wLock.release();
        }
        mutex.release();
    }
    
    public void startWriter() throws InterruptedException {
        wLock.acquire();
    }
    
    public void endWriter() throws InterruptedException {
        wLock.release();
    }
}
