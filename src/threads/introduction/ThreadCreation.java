
package threads.introduction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 * Description: faça um programa que receba um valor indicando um número de 
 * threads a serem instanciadas e teste os dois modos de criar threads em Java.
 */
public class ThreadCreation implements Runnable{
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfThreads = scan.nextInt();
        System.out.println(numberOfThreads);
        int i = 0;
        while (i < numberOfThreads) {
            new Thread(new ThreadCreation()).start();
            i++;
        }
    }

    @Override
    public void run() {
        System.out.println("woooo wo wo wooo wooooo: ghostspeak amulet needed.");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadCreation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
