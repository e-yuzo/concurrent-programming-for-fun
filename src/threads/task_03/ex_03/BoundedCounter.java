package threads.task_03.ex_03;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo Description: escreva um monitor BoundedCounter que possui um
 * valor mínimo e máximo. A classe possui dois métodos: increment() e
 * decrement(). Ao alcançar os limites mínimo ou máximo, a thread que alcançou
 * deve ser bloqueada.
 */
public class BoundedCounter {

    //private int offset = 0;
    //private boolean recorded = true;
    //String[] buffer = new String[6];
    //private int removeIndex = 0;
    private final int max = 10;
    private final int min = -10;
    private int current = 0;
    //private int addIndex = 0;
//    private List<String> buffer = new ArrayList<>();
    //String record;

//    public void clean() {
//        buffer = new ArrayList<>();
//        offset = 0;
//    }
    public synchronized void increment() {
        while (current == max) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BoundedCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        current++;
        if (current > min && current < max) {
            notifyAll();
        }
    }

    public synchronized void decrement() {
        while (current == min) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BoundedCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        current--;
        if (current < max && current > min) {
            notifyAll();
        }
    }

//    public synchronized void addNews(String news) {
//        while (addIndex - removeIndex == buffer.length) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.out.println("Thread interrupted: " + e);
//            }
//        }
//        //recorded = false;
//        buffer[(addIndex++ % buffer.length)] = news;
//        //record = news;
//        notifyAll();
////        buffer.add(news);
//    }
//    
//    public synchronized String getLastNews() {
//        while (addIndex == removeIndex) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.out.println("Thread interrupted: " + e);
//            }
//        }
//        //recorded = true;
//        notifyAll();
//        return buffer[removeIndex++ % 6];
////        int bufferSize = buffer.size();
////        if (offset < bufferSize - 1) {
////            return buffer.get(offset++);
////        }
////        return null;
//    }
}
