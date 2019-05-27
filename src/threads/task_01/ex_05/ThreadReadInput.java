package threads.task_01.ex_05;

import java.util.Scanner;

/**
 *
 * @author yuzo
 * Description: a + b = answer.
 */
public class ThreadReadInput implements Runnable{
    
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
