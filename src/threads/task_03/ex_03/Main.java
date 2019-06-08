package threads.task_03.ex_03;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author yuzo
 * Description: Escreva um monitor BoundedCounter que possui um valor mı́nimo e
 * máximo. A classe possui dois métodos: increment() e decrement(). Ao alcançar
 * os limites mínimo ou máximo, a thread que alcançou deve ser bloqueada.
 */
public class Main {

    public static void main(String[] args) {
        BoundedCounter bc = new BoundedCounter();
        Thread dec = new Thread(new Decrement(bc));
        Thread inc = new Thread(new Increment(bc));
        Thread inc2 = new Thread(new Increment(bc));
        dec.start();
        inc.start();
        inc2.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("dec state: " + dec.getState());
            System.out.println("inc2 state: " + inc2.getState());
            System.out.println("inc state: " + inc.getState() + "\n");
            
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted exception: " + e);
            }
        }
    }
}
