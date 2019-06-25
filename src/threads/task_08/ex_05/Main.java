/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_08.ex_05;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Yuzo Description: Fa¸ca um programa que possibilite agendar uma
 * tarefa para ser executada em um hor´ario espec´ıfico.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        int THREADS = 5;
        System.out.println("This task is able to wait.");
        Scanner tis = new Scanner(System.in);
        System.out.print("(Seconds) Execute task in...: ");
        int TIME = tis.nextInt();
        
        Calendar myDate = Calendar.getInstance();
        myDate.add(Calendar.SECOND, TIME);
        Date DELAY = myDate.getTime();
        long delay = DELAY.getTime() - System.currentTimeMillis();
//        System.out.println(delay);

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(THREADS);
        ScheduledFuture sf = ses.schedule(new Callable() {
            @Override
            public Object call() throws Exception {
                return TIME;
            }
        },
                delay,
                TimeUnit.MILLISECONDS);
        System.out.println("You've waited for " + sf.get() + " seconds.");
        ses.shutdown();
    }
}
