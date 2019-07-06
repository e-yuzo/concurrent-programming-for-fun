package threads.task_09.ex_01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Yuzo Description: Implemente duas vers~oes do problema do
 * produtor/consumidor com M produtores e N consumidores usando
 * ArrayBlockingQueue e LinkedBlockingQueue. Compare o desempenho das duas
 * implementações.
 */
public class Main_Linked {

    public static void main(String[] args) throws InterruptedException {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
//        int poisonPill = Integer.MAX_VALUE;
//        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
//        int mod = N_CONSUMERS % N_PRODUCERS;
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);
        Thread[] threadsProducers = new Thread[N_PRODUCERS];
        Thread[] threadsConsumers = new Thread[N_CONSUMERS];
        long s = System.nanoTime();
        for (int i = 0; i < N_PRODUCERS; i++) {
            Thread t = new Thread(new Producer(queue));
            t.start();
            threadsProducers[i] = t;
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            Thread t = new Thread(new Consumer(queue));
            t.start();
            threadsConsumers[j] = t;
        }

        for (Thread t : threadsProducers) {
            t.join();
        }

        for (Thread t : threadsConsumers) {
            t.join();
        }

        long e = System.nanoTime();
        long timeElapsed = e - s;
        System.out.println(timeElapsed);
//        , poisonPill, poisonPillPerProducer+mod
//        new Thread(new Producer(queue)).start();
    }
}
