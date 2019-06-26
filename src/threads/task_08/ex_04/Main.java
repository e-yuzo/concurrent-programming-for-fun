
package threads.task_08.ex_04;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuzo Description: Fa¸ca um programa que periodicamente monitore
 * mudan¸cas em um conjunto de arquivos especificados. Se ocorreram mudan¸cas, o
 * programa deve registr´a-las em um arquivo de log.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int THREADS = 5;

        Scanner tis = new Scanner(System.in);
        System.out.print("Time in Seconds: ");
        int TIME = tis.nextInt();

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(THREADS);

        Set<Runnable> runnables = new HashSet<>();
        
        File cells = new File(System.getProperty("user.dir") + "/src/threads/task_08/ex_04/cells.txt");
        File strange = new File(System.getProperty("user.dir") + "/src/threads/task_08/ex_04/strange.txt");
        File water = new File(System.getProperty("user.dir") + "/src/threads/task_08/ex_04/water.txt");
        
        Monitor mCells = new Monitor(cells);
        Monitor mstrange = new Monitor(strange);
        Monitor mwater = new Monitor(water);
        
        runnables.add(mCells);
        runnables.add(mstrange);
        runnables.add(mwater);
        
        runnables.forEach((run) -> {
            ses.scheduleWithFixedDelay(run, 0, TIME, TimeUnit.SECONDS);
        });
        
        ses.awaitTermination(100, TimeUnit.SECONDS);
    }
}

class Monitor implements Runnable {

    File file;
    String lastModif = "";

    public Monitor(File file) {
        this.file = file;
    }

    public static String format(long time) {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(new Date(time));
    }

    public void toFileOutputStream(String text) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(System.
                getProperty("user.dir") + "/src/threads/task_08/ex_04/logs.txt", true)) {
            String toAppend = text + "\n";
            byte[] strToBytes = toAppend.getBytes();
            outputStream.write(strToBytes);
        }
    }

    @Override
    public void run() {
        try {
            long lastModified = file.lastModified();
            String time = format(lastModified);
            if (time.equals(lastModified)) {
                System.out.println("Unmodified");;
                toFileOutputStream("FILE " + file.getName() + " MODIFIED AT " + time);
            }
            lastModif = time;
            System.out.println(lastModif);
            toFileOutputStream("FILE " + file.getName() + " MODIFIED AT " + time);
            System.out.println("FILE " + file.getName() + " MODIFIED AT " + time);
        } catch (IOException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
