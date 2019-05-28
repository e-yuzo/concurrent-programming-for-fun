package threads.task_02.ex_03;

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
