package threads.task_07.ex_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Yuzo Description: One-Dimensional Stencil - implementação Latch.
 */
public class Main {

    public static List<Double> latch(int iter, List<Double> list) throws InterruptedException {
        List<Double> newList = Collections.synchronizedList(new ArrayList<>(list));
        List<Double> valList = Collections.synchronizedList(new ArrayList<>(list));
        CountDownLatch countDownLatch;

        for (int i = 1; i < iter; i++) {
            countDownLatch = new CountDownLatch(list.size() - 2);

            for (int j = 1; j < list.size() - 1; j++) {
                Thread t = new Thread(new Worker(j, newList, valList, countDownLatch));
                t.start();
            }
            countDownLatch.await();
            List<Double> temp = newList;
            newList = valList;
            valList = temp;
        }
        return valList;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Double> list = new ArrayList<>(Arrays.asList(
        1.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 10.0));
        System.out.println(latch(1000, list));
    }
}

class Worker implements Runnable {

    int j;
    List<Double> newList;
    List<Double> valList;
    CountDownLatch cdl;

    public Worker(int j, List<Double> newList, List<Double> valList, CountDownLatch cdl) {
        this.j = j;
        this.newList = newList;
        this.valList = valList;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        newList.set(j, (valList.get(j - 1) + valList.get(j + 1)) / 2.0);
        cdl.countDown();
    }

}
