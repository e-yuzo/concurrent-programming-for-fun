package threads.task_09.ex_01;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Yuzo
 */
public class Consumer implements Runnable {

    private final BlockingQueue<Integer> queue;
//    private final int poisonPill;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
//        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i != 1000 - 1) {
                
                Integer number = queue.take();
                i = number;
//                if (number.equals(poisonPill)) {
//                    return;
//                }
                String result = number.toString();
                System.out.println(Thread.currentThread().getName()
                        + " result: " + result);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
