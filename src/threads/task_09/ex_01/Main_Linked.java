
package threads.task_09.ex_01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Yuzo
 * Description: Implemente duas vers~oes do problema do produtor/consumidor com
 * M produtores e N consumidores usando ArrayBlockingQueue e LinkedBlockingQueue.
 * Compare o desempenho das duas implementações.
 */
public class Main_Linked {
    
    public static void main(String[] args) {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
//        int poisonPill = Integer.MAX_VALUE;
//        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
//        int mod = N_CONSUMERS % N_PRODUCERS;
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        for (int i = 1; i < N_PRODUCERS; i++) {
            new Thread(new Producer(queue)).start();
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new Consumer(queue)).start();
        }
//        , poisonPill, poisonPillPerProducer+mod
//        new Thread(new Producer(queue)).start();
    }
}
