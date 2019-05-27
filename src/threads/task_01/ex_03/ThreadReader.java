package threads.task_01.ex_03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 */
public class ThreadReader implements Runnable {

    private String readFile(String fileName) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException ex) {
            Logger.getLogger(ThreadReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public void run() {
        while (true) {
            String fileData;
            fileData = readFile("/home/yuzo/NetBeansProjects/ConcurrentProgramming/src/threads/interruption/quotes.txt");
            System.out.println(fileData);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                System.out.println("someone interruped me");
                break;
            }
        }
    }

}
