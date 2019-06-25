/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_05.ex_02;

/**
 *
 * @author yuzo
 * Description: Implementar 3 soluções distintas para o jantar dos filósofos que
 * não causem deadlock.
 */
public class Main {
    public static void main(String[] args) {
        
        int N = 5;
        //change next line to 'V2' or 'V3'
        Thinker philosopher = new DiningPhilosophersV3(N);
        
        for (int i = 0; i < N; i++) {
            new Thread(new Philosopher(i, philosopher)).start();
        }
    }
}
