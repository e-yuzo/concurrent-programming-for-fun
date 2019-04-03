/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.state;

import threads.state.thread_classes.ThreadDoesNothing;
import threads.state.thread_classes.ThreadMonitor;

/**
 *
 * @author yuzo
 * Description: faça um programa em Java que consulte periodicamente o estado de
 * um conjunto de threads.
 */
public class StateMonitor {
    public static void main(String[] args) {
        
        //group creation
        ThreadGroup group = new ThreadGroup("Laziness");

        //thread creation/start
        Thread thread_1 = new Thread(group, new ThreadDoesNothing());
        thread_1.setName("Lazy");
        thread_1.start();
        
        Thread thread_2 = new Thread(group, new ThreadDoesNothing());
        thread_2.setName("Lazy and Hungry");
        thread_2.start();
        
        Thread threadMonitor = new Thread(new ThreadMonitor(group));
        threadMonitor.setName("Monitor");
        threadMonitor.start();
    }
}




