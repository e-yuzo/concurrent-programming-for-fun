package threads.task_02.ex_02;

/**
 *
 * @author yuzo
 * Description: faça um programa em Java para testar um conjunto de
 * operações sobre um grupo de threads. Use o ThreadGroup.
 */
public class GroupThreadOperations {

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("Addiction");
        
        Thread thread_1 = new Thread(group, new ThreadDoesNothing());
        thread_1.setName("run");
        thread_1.start();
        
        Thread thread_2 = new Thread(group, new ThreadDoesNothing());
        thread_2.setName("sky");
        thread_2.start();
        
        System.out.println("Group getMaxPriority: " + group.getMaxPriority());
        group.setMaxPriority(Thread.NORM_PRIORITY + 1);
        System.out.println("Group getMaxPriority after setMaxPriority: " + group.getMaxPriority());
        System.out.println("Group activeCount: " + group.activeCount());
        System.out.println("Group activeGroupCount: " + group.activeGroupCount());
        
        Thread[] threadList = new Thread[group.activeCount()];
        group.enumerate(threadList);
        
        for (int i = 0; i < group.activeCount(); i++) {
            System.out.println(threadList[i] + " isDaemon: " + threadList[i].isDaemon());
        }
        
        group.interrupt();
        group.destroy();
    }
}
