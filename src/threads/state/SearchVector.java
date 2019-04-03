/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import threads.state.thread_classes.ThreadSearchList;

/**
 *
 * @author yuzo
 * Description: faça um programa em Java que realize uma busca paralela em um
 * vetor de inteiros. Informe para o método: valor procurado, vetor de inteiros
 * e o número de threads.
 */
public class SearchVector {
    
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
