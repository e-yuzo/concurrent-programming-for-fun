/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_08.ex_01;

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

/**
 *
 * @author a1354698
 * Fa ̧ca um programa que localize o maior valor em um vetor.  Divida oprograma
 * em tarefas que localizam o maior valor em um segmento dovetor.  O programa
 * deve possibilitar especificar o n ́umero de tarefas e on ́umero de threads
 * para resolver o problema.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5,
                6, 7, 8, 9, 10));
        int TASKS = 3;
        int THREADS = 2;
        
        ExecutorService es = Executors.newSingleThreadExecutor();
        Set<Callable<Integer>> callables = new HashSet<Callable<Integer>>();
       
        
        int indexRange = integerList.size() / TASKS;
        
        for (int i = 0; i < indexRange; i++ ) {
            int start = i * indexRange;
            int end = start + indexRange;
            if (i == indexRange - 1) {
                end = integerList.size();
            }
            List<Integer> subList = integerList.subList(start, end);
            callables.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    subList.sort(Collections.reverseOrder());
                    return subList.get(0);
                }
            });
        }
        
        List<Future<Integer>> futures = es.invokeAll(callables);
        List<Integer> result = new ArrayList();
        for (Future<Integer> future : futures) {
            result.add(future.get());
        }
        result.sort(Collections.reverseOrder());
        System.out.println("Highest number: " + result.get(0));
        es.shutdown();
    }
}

class HighestNumberInVector implements Callable<Integer> {

    List list;
    
    public HighestNumberInVector(List list) {
        this.list = list;
    }

    @Override
    public Integer call() throws Exception {
        
        for (Object value : list) {
            int a = (int) value;
            return a;
        }
        return 0;
    }
}
