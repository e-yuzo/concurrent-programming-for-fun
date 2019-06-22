
package threads.task_08.ex_06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Yuzo Description: Fa¸ca um programa que execute trˆes algoritmos de
 * ordena¸c˜ao para um conjunto de valores e exiba o resultado apenas do
 * algoritmo que finalizar primeiro (use invokeAny).
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        
        Set<Callable<int[]>> callables = new HashSet<>();
        
        int[] array = new int[30];
        int v = 0;
        
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = v;
            v++;
        }
        
        callables.add(new Bubbles(array));
        callables.add(new Levitates(array));
        callables.add(new InTheAir(array));
        
        int[] result = es.invokeAny(callables);
        
        es.shutdown();
        
        System.out.println(Arrays.toString(result));
    }
    
}

class Bubbles implements Callable<int[]> {

    int[] array;

    public Bubbles(int[] array) {
        this.array = array;
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    @Override
    public int[] call() throws Exception {
        bubbleSort(array);
        System.out.println("bubbles");
        return array;
    }

}

class Levitates implements Callable<int[]> {

    int[] array;

    public Levitates(int[] array) {
        this.array = array;
    }
   
    private void mergeSort(int[] a, int n) {
        if (n < 2)
            return;
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        System.arraycopy(a, 0, l, 0, mid);
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(a, l, r, mid, n - mid);
    }

    private void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    @Override
    public int[] call() throws Exception {
        mergeSort(array, array.length);
        System.out.println("levitates");
        return array;
    }

}

class InTheAir implements Callable<int[]> {

    int[] array;

    public InTheAir(int[] array) {
        this.array = array;
    }

    private void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;
        return i + 1;
    }

    @Override
    public int[] call() throws Exception {
        quickSort(array, 0, array.length);
        System.out.println("in the air");
        return array;
    }
}
