/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.state;

import threads.state.thread_classes.ThreadFindPrimes;

/**
 *
 * @author yuzo
 * Description: faça um programa em Java com threads que exiba os números primos
 * entre 0 e 100000.
 */
public class PrimeNumbers {
    
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        new Thread(new ThreadFindPrimes(availableProcessors, 1000)).start();
    }
}
