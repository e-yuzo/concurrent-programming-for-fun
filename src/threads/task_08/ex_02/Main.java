/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_08.ex_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import sun.rmi.server.UnicastServerRef;

/**
 *
 * @author a1354698 Fa ̧ca um programa que localize o maior valor em um vetor.
 * Divida oprograma em tarefas que localizam o maior valor em um segmento
 * dovetor. O programa deve possibilitar especificar o n ́umero de tarefas e on
 * ́umero de threads para resolver o problema.
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
        ExecutorService es = Executors.newSingleThreadExecutor();
        Set<Callable<int[]>> callables = new HashSet<>();
        int[][] matrixResult = new int[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            int[] mm1 = m1[i];
            int[] mm2 = m2[i];
            Sum s = new Sum(mm1, mm2);
            callables.add(s);

        System.out.println(Arrays.toString(matrixResult));

        executor.shutdown();
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
        }
        return r;
    }

}
