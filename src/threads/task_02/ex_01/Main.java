package threads.task_02.ex_01;

/**
 *
 * @author yuzo
 * Description: faça um programa em Java que consulte periodicamente o estado de
 * um conjunto de threads.
 */
public class Main {
    public static void main(String[] args) {
        
        //group creation
        ThreadGroup group = new ThreadGroup("Laziness");

        //thread creation/start
        Thread thread_1 = new Thread(group, new ThreadDoesNothing());
        thread_1.setName("Lazy");
        thread_1.start();
        
        Thread thread_2 = new Thread(group, new ThreadDoesNothing());
        thread_2.setName("Lazy and Hungry");
        thread_2.start();
        
        Thread threadMonitor = new Thread(new ThreadMonitor(group));
        threadMonitor.setName("Monitor");
        threadMonitor.start();
    }
}




