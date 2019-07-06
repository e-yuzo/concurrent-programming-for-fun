package threads.task_00.ex_01;

/**
 *
 * @author yuzo
 * Description: implemente o exemplo anterior usando Lambda Expression.
 */
public class Main {
    
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Hello from a thread!");
        }).start();
    }
}
