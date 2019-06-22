
package threads.task_04.ex_07;

/**
 *
 * @author yuzo
 * Description: Semáforos podem ser usadas para representar uma fila. Faça um
 * código que implemente duas filas (F1 e F2) com semáforos. As threads
 * colocadas na F1 só podem executar se tiver um par na F2. Nesse caso, ambas as
 * threads na primeira fila são executadas.
 */
public class Main {
    
    public static void main(String[] args) {
        int F1 = 4;
        int F2 = 10;
        
        Queue q = new Queue();
 
        Thread[] f1 = new Thread[F1];
        for (int i = 0; i < F1; i++) {
            f1[i] = new Thread(new Thread_F1(q));
            f1[i].setName(Integer.toString(i + 10));
            f1[i].start();
        }
        
        Thread[] f2 = new Thread[F2];
        for (int i = 0; i < F2; i++) {
            f2[i] = new Thread(new Thread_F2(q));
            f2[i].setName(Integer.toString(i + 20));
            f2[i].start();
        }
    }
}
