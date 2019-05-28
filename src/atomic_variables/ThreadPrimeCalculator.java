package atomic_variables;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuzo
 */
public class ThreadPrimeCalculator implements Runnable {

    IntegerPool integerPoolObj;

    public ThreadPrimeCalculator(IntegerPool integerPoolObj) {
        this.integerPoolObj = integerPoolObj;
    }

    public Boolean isPrime(int number) {
        int flag = 0;
        for (int j = 2; j < number; j++) {
            if (number % j == 0) {
                flag = 0;
                break;
            } else {
                flag = 1;
            }
        }
        if (flag == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            int next = integerPoolObj.next();
            if (next != 0) {
                Boolean isPrime = isPrime(next);
                if (isPrime) {
                    integerPoolObj.storePrime(next);
                }
            } else {
                break;
            }
        }
    }
}

class IntegerPool {

    private int end;
    private int currentNumber;
    private final List<Integer> storage = new ArrayList<>();

    public IntegerPool(int start, int end) {
        this.currentNumber = start;
        this.end = end;
    }

    public synchronized int next() { //not-thread-safe
        int value = currentNumber++;
        if (end >= value) {
            return value;
        } else {
            return 0;
        }
    }
//    public int next() { //not-thread-safe
//        int value = 0;
//        synchronized (this) {
//            value = currentNumber++;
//        }
//        if (end >= value) {
//            return value;
//        } else {
//            return 0;
//        }
//    }
//    public int next() { //not-thread-safe
//        int value = currentNumber.incrementAndGet();
//        if (end >= value) {
//            return value;
//        } else {
//            return 0;
//        }
//    }

    public List<Integer> getStorage() {
        return this.storage;
    }

    public synchronized void storePrime(int prime) { //not-thread-safe
        storage.add(prime);
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }
}
