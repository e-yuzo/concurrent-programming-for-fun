package threads.task_02.ex_04;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuzo
 */
public class ThreadSearchList implements Runnable {
    
    private final List<Integer> list;
    private final int number;
    private final int availableProcessors;

    public ThreadSearchList(List<Integer> list, int number, int availableProcessors) {
        this.list = list;
        this.number = number;
        this.availableProcessors = availableProcessors;
    }

    @Override
    public void run() {
        int listSize = this.list.size();
        int interval = listSize / this.availableProcessors;
        List<Integer> load = new ArrayList<>();
        
        int balance = listSize % this.availableProcessors;
        for (int i = 0; i < this.availableProcessors; i++) {
            if (balance == 0) {
                load.add(interval);
            } else {
                load.add(interval + 1);
                balance--;
            }
        }
        
        int index = 0;
        for (int i = 0; i < this.availableProcessors; i++) {
            new Thread(new ThreadFindNumber(index, index + load.get(i), this.list, this.number)).start();
            index = index + load.get(i);
        }
    }
    
}

class ThreadFindNumber implements Runnable {
    
    private final int startIndex;
    private final int endIndex;
    private final List<Integer> list;
    private final int number;

    public ThreadFindNumber(int startIndex, int endIndex, List<Integer> list, int number) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.list = list;
        this.number = number;
    }
    
    @Override
    public void run() {
        for (int i = this.startIndex; i < this.endIndex; i++) {
            if (list.get(i) == this.number) {
                System.out.println("i found the number " + list.get(i) + " :)");
            }
        }
    }
    
}
