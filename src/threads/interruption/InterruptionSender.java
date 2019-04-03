
package threads.interruption;

import java.util.List;
import java.util.Vector;
import threads.interruption.thread_classes.ThreadInterrupter;
import threads.interruption.thread_classes.ThreadReader;
import threads.interruption.thread_classes.ThreadSleeper;

/**
 *
 * @author yuzo
 * Description: Faça um programa Java que envia interrupções para as threads dos
 * exercı́cios anteriores. As threads devem fazer o tratamento dessas interrupções
 * e realizar uma finalização limpa.
 */

public class InterruptionSender {
    
    public static void main(String[] args) {
        //sleeper thread creation
        Thread threadSleeper_1 = new Thread(new ThreadSleeper());
        Thread threadSleeper_2 = new Thread(new ThreadSleeper());
        Thread threadSleeper_3 = new Thread(new ThreadSleeper());
        threadSleeper_1.start();
        threadSleeper_2.start();
        threadSleeper_3.start();

        //reader thread creation
        Thread threadReader = new Thread(new ThreadReader());
        threadReader.start();
       
        List<Thread> threadList = new Vector<Thread>();
        
        //add all threads created
        threadList.add(threadSleeper_1);
        threadList.add(threadSleeper_2);
        threadList.add(threadSleeper_3);
        threadList.add(threadReader);
        
        //start thread interrupter
        new Thread(new ThreadInterrupter(threadList)).start();
    }
}
