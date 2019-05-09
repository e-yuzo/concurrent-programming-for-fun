/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.wall;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Barreira {
    
    Semaphore s;
    
    public Barreira(Semaphore s) {
        this.s = s;
    }
    
    public void wall_e() {
        
    }
}
