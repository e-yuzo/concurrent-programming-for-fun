package threads.task_02.ex_02;

/**
 *
 * @author yuzo
 */
public class ThreadDoesNothing implements Runnable {

    @Override
    public void run() {
        
        //System.out.println(tg.getName());
        while (true) {
            System.out.println("we just wait forever, y'know");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                System.out.println("wow :/");
                break;
            }
        }
    }
}
