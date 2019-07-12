package threads.task_07.ex_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuzo Description: One-Dimensional Stencil - implementação CyclicBarrier.
 * Description: Implementação Phaser o professor disse que não precisava fazer hehe ºoº
 */
public class Main {

    public static List<Double> cyclicBarrier(int iter, List<Double> list) throws InterruptedException, BrokenBarrierException {
        List<Double> newList = Collections.synchronizedList(new ArrayList<>(list));
        List<Double> valList = Collections.synchronizedList(new ArrayList<>(list));
        CyclicBarrier cyclicBarrier;

        for (int i = 1; i < iter; i++) {
            cyclicBarrier = new CyclicBarrier(list.size() - 2);

            for (int j = 1; j < list.size() - 1; j++) {
                Thread t = new Thread(new Worker(j, newList, valList, cyclicBarrier));
                t.start();
            }
            List<Double> temp = newList;
            newList = valList;
            valList = temp;
        }
        return valList;
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        List<Double> list = new ArrayList<>(Arrays.asList(
        1.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 10.0));
        System.out.println(cyclicBarrier(1000, list));
    }
}

class Worker implements Runnable {

    int j;
    List<Double> newList;
    List<Double> valList;
    CyclicBarrier cdl;

    public Worker(int j, List<Double> newList, List<Double> valList, CyclicBarrier cdl) {
        this.j = j;
        this.newList = newList;
        this.valList = valList;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        newList.set(j, (valList.get(j - 1) + valList.get(j + 1)) / 2.0);
        try {
            cdl.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
