/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption.thread_classes;

/**
 *
 * @author yuzo
 */
public class ThreadInterrupted implements Runnable{
    
    private Boolean interrupted = false;

    @Override
    public void run() {
        while (!this.interrupted) {
            
        }
    }
}
