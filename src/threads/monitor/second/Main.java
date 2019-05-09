/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.monitor.second;

/**
 *
 * @author yuzo
 * Description: Escreva uma monitor Counter que possibilita um processo dormir
 * até o contador alcançar um valor. A classe Counter permite duas operações:
 * increment() e sleepUntil(int x).
 */
public class Main {
    
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread increment = new Thread(new Increment(counter));
        Thread sleeper1 = new Thread(new Sleeper(counter, 10));
        Thread sleeper2 = new Thread(new Sleeper(counter, 5));
        sleeper1.start();
        sleeper2.start();
        increment.start();
    }
}
