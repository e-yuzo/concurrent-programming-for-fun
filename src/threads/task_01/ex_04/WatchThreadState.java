package threads.task_01.ex_04;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuzo
 * Description: faça uma Thread que monitora um conjunto de threads e exiba quais
 * threads receberam sinais de interrupção.
 */
public class WatchThreadState {
   
    public static void main(String[] args) {
        Thread threadInterrupted = new Thread(new ThreadInterrupted());
        
        List<Thread> threadList = new ArrayList<>();
        threadList.add(threadInterrupted);
        
        Thread threadInterrupter = new Thread(new ThreadInterrupter(threadList));
        Thread threadWatcher = new Thread(new ThreadWatcher(threadList));
        
        threadInterrupted.start();
        threadWatcher.start();
        threadInterrupter.start();
    }
}
