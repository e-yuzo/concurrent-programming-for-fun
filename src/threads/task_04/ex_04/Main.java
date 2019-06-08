package threads.task_04.ex_04;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 * Description: Garantir acesso à seção crítica para no máximo N threads. Faça
 * um código que possibilite que N threads estejam na seção crítica
 * simultaneamente.
 */
public class Main {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(3); //three threads can access
        Counter c = new Counter(s);
        
        Thread t1 = new Thread(new Increment(c));
        Thread t2 = new Thread(new Increment(c));
        Thread t3 = new Thread(new Increment(c));
        t1.start();
        t2.start();
        t3.start();
    }
}
