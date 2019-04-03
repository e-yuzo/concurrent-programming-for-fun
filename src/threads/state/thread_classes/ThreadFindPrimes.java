/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.state.thread_classes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author yuzo
 */
public class ThreadFindPrimes implements Runnable {

    private int availableProcessors;
    private int numberOfPrimes;

    public ThreadFindPrimes(int availableProcessors, int numberOfPrimes) {
        this.availableProcessors = availableProcessors;
        this.numberOfPrimes = numberOfPrimes;
    }

    @Override
    public void run() {
        int interval = this.numberOfPrimes / this.availableProcessors;
        List<Integer> listIntervals = new Vector<Integer>();
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

    public static List<Integer> sieveOfEratosthenes(int start, int end) {
        boolean prime[] = new boolean[end + 1];
        Arrays.fill(prime, true);
        for (int p = start; p * p <= end; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= end; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = start; i <= end; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    @Override
    public void run() {
        List<Integer> primes = sieveOfEratosthenes(this.firstNumber, this.lastNumber);
        System.out.println(primes);
    }

}
