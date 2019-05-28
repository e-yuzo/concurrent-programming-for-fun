package threads.task_04.ex_03;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 */
public class Mutex {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1); //quantidade de threads acessando o mesmo recurso ????
        Counter c = new Counter(s);
        
        Thread t1 = new Thread(new Increment(c));
        Thread t2 = new Thread(new Increment(c));
        t1.start();
        t2.start();
    }
}
