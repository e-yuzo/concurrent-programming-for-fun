/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.thread_safety_sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author yuzo
 * Description: 
 * 1: faça um programa em Java que use Threads para encontrar os
 * números primos dentro de um intervalo. O método que contabiliza os números
 * primos deve possuir como entrada: valor inicial e final do intervalo, núumero
 * de threads.
 * 2: modifique o código para garantir que será thread-safe. Implemente três
 * versões: usando Atomic, sincronizando o método e sincronizando o bloco.
 * 3: para o exercı́cio anterior, compare o desempenho medindo o tempo de início e
 * término para processar o intervalo.
 * Synchronized method: 1.010.315.573 nanoseconds. 1. ...s
 * Synchronized block:  891.273.960 nanoseconds.   0.8 ...s
 * Atomic:              835.867.195 nanoseconds.   0.8 ...s
 */
public class PrimeNumberRace {

    public static void main(String[] args) {
        int numberThreads = 10;
        int start = 2;
//        AtomicInteger start = new AtomicInteger(2);
        int end = 100000;
        IntegerPool pool = new IntegerPool(start, end);
        List<Thread> threadList = new ArrayList<>();

        long s = System.nanoTime();
        for (int i = 0; i < numberThreads; i++) {
            Thread thread = new Thread(new ThreadPrimeCalculator(pool));
            thread.start();
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(PrimeNumberRace.class.getName()).log(Level
                        .SEVERE, null, ex);
            }
        }
        long e = System.nanoTime();
        long timeElapsed = e - s;
        System.out.println(pool.getStorage());
        System.out.println(timeElapsed);
    }
}