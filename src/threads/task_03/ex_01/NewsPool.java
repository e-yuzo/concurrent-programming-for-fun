package threads.task_03.ex_01;

/**
 *
 * @author yuzo
 */
public class NewsPool {

    String[] buffer = new String[6];
    private int removeIndex = 0;
    private int addIndex = 0;
    
    public synchronized void addNews(String news) {
        while (addIndex - removeIndex == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
        buffer[(addIndex++ % buffer.length)] = news;
        notifyAll();
    }
    
    public synchronized String getLastNews() {
        while (addIndex == removeIndex) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
        notifyAll();
        return buffer[removeIndex++ % buffer.length];
    }
    
}
