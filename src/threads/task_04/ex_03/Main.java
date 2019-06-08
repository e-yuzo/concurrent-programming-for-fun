package threads.task_04.ex_03;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 * Description: Garantir acesso exclusivo à seção crítica. Faça um código que
 * possibilite que 2 ou mais threads realizem o incremento de um contador. Faça
 * a exclusão mútua com semáforo.
 */
public class Main {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1); //quantidade de threads acessando o mesmo recurso ????
        Counter c = new Counter(s);
        
        Thread t1 = new Thread(new Increment(c));
        Thread t2 = new Thread(new Increment(c));
        t1.start();
        t2.start();
    }
}
