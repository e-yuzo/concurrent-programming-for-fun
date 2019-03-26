/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.introduction;

/**
 *
 * @author a1354698
 * Description: thread safety.
 */

public class ThreadSafety {
    
    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(new SharedObject(counter)).start();
        new Thread(new SharedObject(counter)).start();
        new Thread(new SharedObject(counter)).start();
    }
}

class SharedObject implements Runnable {

    Counter counter;

    public SharedObject(Counter counter) {
        this.counter = counter;
    }      
        
    private void bug() {
        counter.add();
    }
    
    @Override
    public void run() {
        while (true) {
            bug();
            System.out.println(counter.getValue());
        }
    }
}

class Counter {
    
    private int value;
    
    public Counter() {
        this.value = 0;
    }
    
    public void add() {
        this.value++;
    }

    public int getValue() {
        return value;
    }
}


