package threads.task_02.ex_05;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadMergeSort implements Runnable {

    private int list[];
    private int startBlock[];
    private int endBlock[];
    private int sortedList[];

    public ThreadMergeSort(int list[]) {
        this.list = list;
    }

    public void joinSortedLists() {
        int startBlockSize = startBlock.length;
        int endBlockSize = endBlock.length;
        sortedList = new int[startBlockSize + endBlockSize];
        int i = 0, j = 0, k = 0;
        while (i < startBlockSize && j < endBlockSize) {
            if (startBlock[i] < endBlock[j]) {
                sortedList[k++] = startBlock[i++];
            } else {
                sortedList[k++] = endBlock[j++];
            }
        }
        while (i < startBlockSize) {
            sortedList[k++] = startBlock[i++];
        }
        while (j < endBlockSize) {
            sortedList[k++] = endBlock[j++];
        }
    }

    public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        int halfIndex = list.length / 2;

        ThreadSort threadSortFirstHalf = new ThreadSort(Arrays.copyOfRange(list, 0, halfIndex));
        Thread threadFirstHalf = new Thread(threadSortFirstHalf);
        threadFirstHalf.start();
        ThreadSort threadSortSecondHalf = new ThreadSort(Arrays.copyOfRange(list, halfIndex, list.length));
        Thread threadSecondHalf = new Thread(threadSortSecondHalf);
        threadSecondHalf.start();

        try {
            threadFirstHalf.join();
            threadSecondHalf.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadMergeSort.class.getName()).log(Level.SEVERE, null, ex);
        }

        startBlock = threadSortFirstHalf.getSortedList();
        endBlock = threadSortSecondHalf.getSortedList();

        joinSortedLists();
        for (int n : sortedList) {
            System.out.println(n);
        }
    }
}

class ThreadSort implements Runnable {

    private int list[];
    private int sortedList[];

    public ThreadSort(int list[]) {
        this.list = list;
    }

    public int[] getSortedList() {
        return sortedList;
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    @Override
    public void run() {
        mergeSort(list, list.length);
        sortedList = list;
    }
}
