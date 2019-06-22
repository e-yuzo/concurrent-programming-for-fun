/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_08.ex_02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author a1354698
 * Description: Fa¸ca um programa que calcule a soma dos elementos de uma matriz
 * MxN. Divida o programa em tarefas que somam as linhas. O programa deve
 * possibilitar especificar o n´umero de threads para resolver o problema.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int m1[][] = {{1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12}};

        int m2[][] = {{1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12}};

        int THREADS = 5;

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
//        ExecutorService es = Executors.newSingleThreadExecutor();
        CompletionService<int[]> completionService = new ExecutorCompletionService<>(executor);
//        Set<Callable<int[]>> callables = new HashSet<>();
        List<Future<int[]>> futures = new ArrayList<>();
        int[][] matrixResult = new int[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            int[] mm1 = m1[i];
            int[] mm2 = m2[i];
            Sum s = new Sum(mm1, mm2);
            Future<int[]> future = executor.submit(s);
            futures.add(future);
//            callables.add(s);
        }
        
        int i = 0;
        executor.shutdown();
//        executor.invokeAll(callables);
        for (Future<int[]> f : futures) {
            try {
                matrixResult[i] = f.get();
                i++;
            } catch (InterruptedException | ExecutionException ex) {
            }
        }
        for (int[] matrixResult1 : matrixResult) {
            for (int k = 0; k < matrixResult[0].length; k++) {
                System.out.print(matrixResult1[k] + " ");
            }
            System.out.println();
        } 
    }
}

class Sum implements Callable<int[]> {

    int[] m1;
    int[] m2;

    public Sum(int[] m1, int[] m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    public int[] call() throws Exception {
        int[] r = new int[m1.length];
        for (int i = 0; i < m1.length; i++) {
            r[i] = m1[i] + m2[i];
            System.out.println(r[i]);
        }
        return r;
    }
}
