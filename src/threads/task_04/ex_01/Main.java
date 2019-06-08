package threads.task_04.ex_01;

import java.util.concurrent.Semaphore;

/**
 *
 * @author yuzo
 * Enviar sinal para outra thread para indicar que um evento ocorreu. Faça um
 * código que uma thread t1 e t2 são inicializadas simultaneamente, mas a t2
 * pode somente continuar a execução após a sinalização de t1.
 */
public class Main {
    
    public static void main(String[] args) {
        Semaphore s = new Semaphore(0);
        Thread t1 = new Thread(new Thread_1(s));
        Thread t2 = new Thread(new Thread_2(s));
        t1.start();
        t2.start();
    }
}
