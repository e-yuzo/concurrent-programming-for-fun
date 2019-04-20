
package threads.producer_consumer;

/**
 *
 * @author yuzo
 * Description: Implemente o problema do produtor-consumidor que há um buffer
 * compartilhado entre threads. Há uma única thread produtora e uma única
 * consumidora. O buffer é preenchido em tempos aleatórios pela thread produtora.
 * Assim que for produzido algo, a thread consumidora deve ser comunicada para
 * obter o valor.
 */
public class InitiateThreads {
    
    public static void main(String[] args) {
        NewsPool newsPool = new NewsPool();
        Thread producer = new Thread(new ProducerThread(newsPool));
        Thread consumer = new Thread(new ConsumerThread(newsPool));
        
        producer.start();
        consumer.start();
    }
}
