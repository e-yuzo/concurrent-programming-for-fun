/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

import java.util.List;
import java.util.Vector;
import threads.interruption.thread_classes.ThreadInterrupted;
import threads.interruption.thread_classes.ThreadInterrupter;
import threads.interruption.thread_classes.ThreadWatcher;

/**
 *
 * @author yuzo
 * Description: faça uma Thread que monitora um conjunto de threads e exiba quais
 * threads receberam sinais de interrupção.
 */
public class WatchThreadState {
   
    public static void main(String[] args) {
        Thread threadInterrupted = new Thread(new ThreadInterrupted());
        
        List<Thread> threadList = new Vector<Thread>();
        threadList.add(threadInterrupted);
        
        Thread threadInterrupter = new Thread(new ThreadInterrupter(threadList));
        Thread threadWatcher = new Thread(new ThreadWatcher(threadList));
        
        threadInterrupted.start();
        threadWatcher.start();
        threadInterrupter.start();
    }
}
