package threads.task_02.ex_04;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuzo
 * Description: faça um programa em Java que realize uma busca paralela em um
 * vetor de inteiros. Informe para o método: valor procurado, vetor de inteiros
 * e o número de threads.
 */
public class Main {
    
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        
        int findNumber = 75;
        
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        
        new Thread(new ThreadSearchList(list, findNumber, availableProcessors)).start();
    }
    
}
