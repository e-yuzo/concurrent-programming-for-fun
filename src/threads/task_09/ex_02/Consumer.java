
package threads.task_09.ex_02;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import javafx.util.Pair;

/**
 *
 * @author Yuzo
 */
public class Consumer implements Runnable {

    CardPool cp;

    public Consumer(BlockingQueue<Pair<String, String>> queue,
            List<Integer> cons, CardPool cp) {
        this.cp = cp;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Pair<String, String> card = cp.take();
                String suit = card.getKey();
                String rank = card.getValue();
                System.out.println(Thread.currentThread().getName()
                        + " result: " + rank + " of " + suit);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
