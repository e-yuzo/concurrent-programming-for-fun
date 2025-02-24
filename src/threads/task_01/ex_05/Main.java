package threads.task_01.ex_05;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 * Description: faça uma thread Java que fica aguardando uma sequência numérica
 * de tamanho arbitrário digitado por usuário para realizar uma soma. Use o join().
 */
public class Main {
    
    public static void main(String[] args) {
        ThreadReadInput readInput = new ThreadReadInput();
        Thread thread = new Thread(readInput);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(readInput.getA() + readInput.getB());
    }
}
