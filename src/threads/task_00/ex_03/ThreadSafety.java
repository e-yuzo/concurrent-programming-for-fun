package threads.task_00.ex_03;

/**
 *
 * @author yuzo
 * Description: implemente o exemplo de código que gera a condição de disputa e
 * tente gerar um teste para que ocorra um problema de segurança (safety).
 */
public class ThreadSafety {
    
    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(new SharedObject(counter)).start();
        new Thread(new SharedObject(counter)).start();
        new Thread(new SharedObject(counter)).start();
    }
}

class SharedObject implements Runnable {

    private final Counter counter;

    public SharedObject(Counter counter) {
        this.counter = counter;
    }      
        
    private void bug() {
        counter.add();
    }
    
    @Override
    public void run() {
        while (true) {
            bug();
            System.out.println(counter.getValue());
        }
    }
}

class Counter {
    
    private int value;
    
    public Counter() {
        this.value = 0;
    }
    
    public void add() {
        this.value++;
    }

    public int getValue() {
        return value;
    }
}


