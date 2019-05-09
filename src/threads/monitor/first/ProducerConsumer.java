
package threads.monitor.first;

/**
 *
 * @author yuzo
 * Description: Implemente uma solução com monitor para o problema do
 * produtor-consumidor usando um buffer circular.
 */
public class ProducerConsumer {
    
    public static void main(String[] args) {
        NewsPool newsPool = new NewsPool();
        Thread producer = new Thread(new Producer(newsPool));
        Thread consumer = new Thread(new Consumer(newsPool));
        
        producer.start();
        consumer.start();
    }
}
