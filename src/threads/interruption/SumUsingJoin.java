/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

import java.util.Scanner;

/**
 *
 * @author yuzo
 * Description: a + b = answer.
 */
public class SumUsingJoin implements Runnable{
    
    private int a;
    private int b;
    
    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        this.a = scan.nextInt();
        scan = new Scanner(System.in);
        this.b = scan.nextInt();
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
