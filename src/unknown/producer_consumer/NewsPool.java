package unknown.producer_consumer;

/**
 *
 * @author yuzo
 */
public class NewsPool {
    
//    private int offset = 0;
    private boolean recorded = true;
//    private List<String> buffer = new ArrayList<>();
    String record;
    
//    public void clean() {
//        buffer = new ArrayList<>();
//        offset = 0;
//    }
    
    public synchronized void addNews(String news) {
        while (!recorded) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
        recorded = false;
        record = news;
        notifyAll();
//        buffer.add(news);
    }
    
    public synchronized String getLastNews() {
        while (recorded) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
        recorded = true;
        notifyAll();
        return record;
//        int bufferSize = buffer.size();
//        if (offset < bufferSize - 1) {
//            return buffer.get(offset++);
//        }
//        return null;
    }
}
