/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_08.ex_03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 *
 * @author a1354698 
 * Description: Fa¸ca um programa concorrente para multiplicar duas matrizes.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int m1[][] = {{1, 2, 3},
        {5, 6, 7},
        {9, 10, 11}};

        int m2[][] = {{1, 2, 3},
        {5, 6, 7},
        {9, 10, 11}};

        int THREADS = 5;

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        List<Future<Integer>> futures = new ArrayList<>();
        int[][] matrixResult = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                Mutiply s = new Mutiply(m1, m2, i, j);
                Future<Integer> future = executor.submit(s);
                futures.add(future);

            }
        }

        executor.shutdown();
        int idx = 0;

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                matrixResult[i][j] = futures.get(idx).get();
                idx++;
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

class Mutiply implements Callable<Integer> {

    int[][] m1;
    int[][] m2;
    int i;
    int j;

    public Mutiply(int[][] m1, int[][] m2, int i, int j) {
        this.m1 = m1;
        this.m2 = m2;
        this.i = i;
        this.j = j;
    }

    int[] getColumn(int[][] matrix, int column) {
        return IntStream.range(0, matrix.length)
                .map(i -> matrix[i][column]).toArray();
    }

    @Override
    public Integer call() throws Exception {
        int[] line = m1[i];
        int[] column = getColumn(m2, j);
        int sum = 0;
        for (int i = 0; i < line.length; i++) {
            sum += (line[i] * column[i]);
        }
        return sum;
    }
}
