
package threads.introduction;

/**
 *
 * @author yuzo
 * Description: implemente o exemplo anterior usando Lambda Expression.
 */
public class ThreadLambda {
    
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Hello from a thread!");
        }).start();
    }
}
