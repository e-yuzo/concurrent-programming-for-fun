package threads.task_02.ex_05;

/**
 *
 * @author yuzo
 * Description: faça um programa multithreaded em Java que ordene um vetor usando
 * o Merge Sort recursivo. Faça com que a thread principal dispare duas threads
 * para classificar cada metade do vetor.
 */
public class Main {
    
    public static void main(String[] args) {
        int[] list = { 5, 1, 6, 2, 3, 4, 9, 9 };
        
        new Thread(new ThreadMergeSort(list)).start();
    }
}
