/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo
 * Description: read and display content stored in file quote.txt (10 seconds 
 * interval).
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
                Logger.getLogger(ThreadReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Thread.interrupted()) {
                System.out.println("ThreadReader interrupted.");
            }
        }
    }

}
