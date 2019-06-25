/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_09.ex_02;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.util.Pair;

/**
 *
 * @author Yuzo
 */
public class CardPool {

    private final BlockingQueue<Pair<String, String>> queue;
    List<Integer> consumerism; // make these variables atomic
    private final AtomicInteger counter = new AtomicInteger();
    int[] consumers = new int[2];

    public CardPool(BlockingQueue<Pair<String, String>> queue,
            List<Integer> consumerism) {
        this.queue = queue;
        this.consumerism = consumerism;
    }

    public void put(Pair<String, String> card) throws InterruptedException {
        boolean flag = false;
        while (!flag) {
            if (getCounter() < 10) {
                queue.put(card);
                System.out.println("Inserted: " + (getCounter() + 1));
                incrementCounter();
                flag = true;
            }
        }
    }

    public Pair<String, String> take() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        char lastChr = threadName.charAt(threadName.length() - 1);
        int threadId = Character.getNumericValue(lastChr);
        while (true) {
            if (getCounter() >= 10 && canConsumeMore(threadId)) {
                System.out.println("Taken: " + threadId);
                if (!canConsumeMore()) {
                    resetCounter();
                    resetLife();
                }
                return queue.take();
            }
        }
    }

    public int getCounter() {
        return counter.get();
    }
    
    public synchronized boolean canConsumeMore() {
        return consumers[0] < 3 || consumers[1] < 3;
    }
//    public synchronized boolean canConsumeMore() {
//        return consumerism.get(0) < 3 || consumerism.get(1) < 3;
//    }

//    public synchronized boolean canConsumeMore(int threadId) {
//        System.out.println(consumerism.toString());
//        if (consumerism.get(threadId - 1) < 3) {
//            consumerism.add(threadId - 1, consumerism.get(threadId - 1) + 1);
//            return true;
//        }
//        return false;
//    }
    public synchronized boolean canConsumeMore(int threadId) {
//        System.out.println(Arrays.toString(consumers));
        if (consumers[threadId - 1] < 3) {
            consumers[threadId - 1] = consumers[threadId - 1] + 1;
            return true;
        }
        return false;
    }

//    public void resetLife() {
//        consumerism.add(0, 0);
//        consumerism.add(1, 0);
//    }
    public synchronized void resetLife() {
        consumers[0] = 0;
        consumers[1] = 0;
//        consumerism.add(1, 0);
    }

    public void resetCounter() {
        while (true) {
            int existingValue = getCounter();
            int newValue = 0;
            if (counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public void incrementCounter() {
        while (true) {
            int existingValue = getCounter();
            int newValue = existingValue + 1;
            if (counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

//    public Collection<Pair<String, String>> take() throws InterruptedException {
//        Collection<Pair<String, String>> cards = new ArrayList<>();
//        if (count >= 10) {
//            queue.drainTo(cards, 10);
//            return cards;
//        } else {
//            
//        }
//    }
}
