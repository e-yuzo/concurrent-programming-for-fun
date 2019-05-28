
package unknown.producer_consumer;

/**
 *
 * @author yuzo
 */
public class ConsumerThread implements Runnable {
    
    NewsPool newsPool;

    public ConsumerThread(NewsPool newsPool) {
        this.newsPool = newsPool;
    }

    @Override
    public void run() {
        while (true) {
            String news = newsPool.getLastNews();
            System.out.println(news);
//            try {
//                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.out.println("Interrupted exception: " + e);
//            }
        }
    }
}
