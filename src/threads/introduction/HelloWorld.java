/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.introduction;

/**
 *
 * @author a1354698
 * Description: Hello World!
 */

public class HelloWorld {
    
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Hello from a thread!");
        }).start();
    }
}
