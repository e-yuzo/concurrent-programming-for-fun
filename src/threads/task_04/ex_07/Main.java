package threads.task_04.ex_07;

/**
 *
 * @author yuzo
 */
public class Main {
    
    public static void main(String[] args) {
        int F1 = 2;
        int F2 = 3;
        
        Queue q = new Queue();
        
        new Thread(new Thread_F1(q)).start();
        new Thread(new Thread_F1(q)).start();
        new Thread(new Thread_F2(q)).start();
        new Thread(new Thread_F2(q)).start();
        new Thread(new Thread_F2(q)).start();
        
//        Thread[] f1 = new Thread[F1];
//        for (int i = 0; i < F1; i++) {
//            f1[i] = new Thread(new Thread_F1(q));
//            f1[i].setName(Integer.toString(i + 10));
//            f1[i].start();
//        }
//        
//        Thread[] f2 = new Thread[F2];
//        for (int i = 0; i < F2; i++) {
//            f2[i] = new Thread(new Thread_F2(q));
//            f2[i].setName(Integer.toString(i + 20));
//            f2[i].start();
//        }
    }
}
