/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

import java.util.List;

/**
 *
 * @author yuzo
 */
public class RandomSleep {
    
    public static void main(String[] args) {
        Thread firstThread = new Thread(new ThreadSleeper());
        Thread secondThread = new Thread(new ThreadSleeper());
        Thread thirdThread = new Thread(new ThreadSleeper());
        List<Thread> threadList = null;
        threadList.add(firstThread);
        threadList.add(secondThread);
        threadList.add(thirdThread);
        
        new Thread(new ThreadInterrupter(threadList)).start();
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        
        

    }
    
}
