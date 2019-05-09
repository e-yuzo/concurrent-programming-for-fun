/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.monitor.second;

/**
 *
 * @author yuzo
 */
public class Sleeper implements Runnable{
    
    Counter c;
    int sleepUntil;
    
    public Sleeper(Counter c, int sleepUntil) {
        this.c = c;
        this.sleepUntil = sleepUntil;
    }

    @Override
    public void run() {
        c.sleepUntil(sleepUntil);
        System.out.println("Finished counter: " + sleepUntil);
    }
}
