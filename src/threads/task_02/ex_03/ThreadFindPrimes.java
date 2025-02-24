package threads.task_02.ex_03;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuzo
 */
public class ThreadFindPrimes implements Runnable {

    private final int availableProcessors;
    private final int numberOfPrimes;

    public ThreadFindPrimes(int availableProcessors, int numberOfPrimes) {
        this.availableProcessors = availableProcessors;
        this.numberOfPrimes = numberOfPrimes;
    }

    @Override
    public void run() {
        int interval = this.numberOfPrimes / this.availableProcessors;
        int first = 2;
        int last = interval;
        for (int i = 0; i < this.availableProcessors; i++) {
            new Thread(new ThreadFindPrimesRange(first, last)).start();
            first = last + 1;
            last = last + interval;
            if (this.numberOfPrimes - last < this.availableProcessors) {
                last = this.numberOfPrimes;
            }
        }
    }
    
}

class ThreadFindPrimesRange implements Runnable {

    private int firstNumber;
    private int lastNumber;

    public ThreadFindPrimesRange(int firstNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
    }

    public static List<Integer> findPrimes(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        int count = 0;
        for (int i = start; i <= end; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    count = 0;
                    break;
                } else {
                    count = 1;
                }
            }
            if (count == 1) {
                primes.add(i);
            }
        }
        return primes;
    }

    @Override
    public void run() {
        List<Integer> primes = findPrimes(this.firstNumber, this.lastNumber);
        System.out.println(primes);
    }

}
