package threads.task_01.ex_01;

/**
 *
 * @author yuzo
 * Description: faça um programa em Java que inicie três threads e, cada thread,
 * espere um tempo aleatório para terminar.
 */
public class RandomSleep {
    
    public static void main(String[] args) {
        //thread sleeper creation
        Thread threadSleeper_1 = new Thread(new ThreadSleeper());
        Thread threadSleeper_2 = new Thread(new ThreadSleeper());
        Thread threadSleeper_3 = new Thread(new ThreadSleeper());
        
        //start threads
        threadSleeper_1.start();
        threadSleeper_2.start();
        threadSleeper_3.start();
    }
}
