package threads.task_04.ex_02;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 * Description:
 * Enviar sinalização para um ponto de encontro entre threads. 
 * Faça um código que uma thread t1 e t2 são inicializadas e t1 espera por t2 e
 * vice-versa.
 * Exemplo:
 * t1:
 * trecho1.1
 * trecho1.2
 * t2:
 * trecho2.1
 * trecho2.2
 * thecho1.1 ocorre antes trecho2.2 e threcho2.1 ocorre antes de trecho1.2.
 */
public class Main {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(0, true);
        Thread t1 = new Thread(new Thread_1(s));
        Thread t2 = new Thread(new Thread_2(s));
        t1.start();
        t2.start();
    }
}
