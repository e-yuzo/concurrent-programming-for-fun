
package threads.task_09.ex_02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;

/**
 *
 * @author Yuzo
 */
public class Producer implements Runnable {

    CardPool cp;

    public Producer(BlockingQueue<Pair<String, String>> queue, CardPool cp) {
        this.cp = cp;
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
        DeckOfCards doc = new DeckOfCards();
        for (int i = 0; i < 100; i++) {
            Pair<String, String> card = new Pair<>(doc.getRandomSuit(), doc.getRandomRank());
            cp.put(card);
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        }
    }
    
}

