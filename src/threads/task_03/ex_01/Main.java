package threads.task_03.ex_01;

/**
 *
 * @author yuzo
 * Description: Implemente uma solução com monitor para o problema do
 * produtor-consumidor usando um buffer circular.
 */
public class Main {
    
    public static void main(String[] args) {
        NewsPool newsPool = new NewsPool();
        Thread producer = new Thread(new Producer(newsPool));
        Thread consumer = new Thread(new Consumer(newsPool));
        
        producer.start();
        consumer.start();
    }
    
}
