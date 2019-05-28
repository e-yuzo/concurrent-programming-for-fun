package threads.task_03.ex_01;

/**
 *
 * @author yuzo
 */
public class NewsPool {
    
    //private int offset = 0;
    //private boolean recorded = true;
    String[] buffer = new String[6];
    private int removeIndex = 0;
    private int addIndex = 0;
//    private List<String> buffer = new ArrayList<>();
    //String record;
    
//    public void clean() {
//        buffer = new ArrayList<>();
//        offset = 0;
//    }
    
    public synchronized void addNews(String news) {
        while (addIndex - removeIndex == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
        //recorded = false;
        buffer[(addIndex++ % buffer.length)] = news;
        //record = news;
        notifyAll();
//        buffer.add(news);
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
        //recorded = true;
        notifyAll();
        return buffer[removeIndex++ % buffer.length];
//        int bufferSize = buffer.size();
//        if (offset < bufferSize - 1) {
//            return buffer.get(offset++);
//        }
//        return null;
    }
}
