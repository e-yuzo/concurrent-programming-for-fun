/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class SumUsingJoinMain {
    
    public static void main(String[] args) {
        SumUsingJoin sumUsingJoin = new SumUsingJoin();
        Thread thread = new Thread(sumUsingJoin);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SumUsingJoinMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sumUsingJoin.getA() + sumUsingJoin.getB());
    }
}
