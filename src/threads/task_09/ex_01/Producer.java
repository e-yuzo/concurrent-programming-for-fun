package threads.task_09.ex_01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Yuzo
 */
public class Producer implements Runnable {

    private final BlockingQueue<Integer> numbersQueue;
//    private final int poisonPill;
//    private final int poisonPillPerProducer;
//, int poisonPill, int poisonPillPerProducer

    public Producer(BlockingQueue<Integer> numbersQueue) {
        this.numbersQueue = numbersQueue;
//        this.poisonPill = poisonPill;
//        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    @Override
    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void generateNumbers() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
//            numbersQueue.put(ThreadLocalRandom.current()
//                .nextInt(100));
            numbersQueue.put(i);
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
        }
//        for (int j = 0; j < poisonPillPerProducer; j++) {
//            numbersQueue.put(poisonPill);
//        }
    }
}
