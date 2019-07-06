package threads.task_01.ex_03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private ArrayList<String> readFileIntoArray(String fileName) throws FileNotFoundException, IOException {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        }
        return result;
    }

    @Override
    public void run() {
        int i = 0;
        ArrayList<String> fileData = null;
        try {
            fileData = readFileIntoArray(System.getProperty("user.dir") + "/src/threads/task_01/ex_03/quotes.txt");
        } catch (IOException ex) {
            Logger.getLogger(ThreadReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            System.out.println(fileData.get(i));
            i = ++i % fileData.size();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
                break;
            }
        }
    }

}
