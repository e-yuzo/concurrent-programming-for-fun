/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

/**
 *
 * @author yuzo
 */
public class DisplayPhrases {
    
    public static void main(String[] args) {
        new Thread(new ThreadReader()).start();
    }
    
}
