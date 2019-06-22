/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_06.ex_02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author yuzo
 */
public class Main {
    
}

class SharedFifoQueue {
    
    Lock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    Condition full = lock.newCondition();
    
    Object[] queue = new Object[100];
    
    public void produce() {
        
    }
    
    public void consume() {
        
    }
}

