package threads.task_03.ex_01;

/**
 *
 * @author yuzo
 */
public class Consumer implements Runnable {
    
    NewsPool newsPool;

    public Consumer(NewsPool newsPool) {
        this.newsPool = newsPool;
    }

    @Override
    public void run() {
        while (true) {
            String news = newsPool.getLastNews();
            System.out.println(news);
        }
    }
    
}
