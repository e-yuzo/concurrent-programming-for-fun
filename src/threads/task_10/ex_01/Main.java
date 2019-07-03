/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_10.ex_01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuzo
 * Fa¸ca um programa usando Threads e ConcurrentMap para calcular a frequˆencia
 * de cada letra em um texto.
 */
public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        try {
            Map<Character, Integer> frequency = new ConcurrentHashMap<>();
            int THREADS = 3;
            
            String contents = new String(Files.readAllBytes(Paths.
                    get(System.getProperty("user.dir")
                            + "/src/threads/task_10/ex_01/water.txt")));
            System.out.println(contents);
            int contentsLength = contents.length();
            
            int range = contentsLength / THREADS;
            System.out.println(contentsLength);
            Thread[] helpers = new Thread[THREADS];
            for (int i = 0; i < THREADS; i++) {
                int start = i * range;
                int end = (i * range) + range;
                if (i == THREADS - 1) { //last iteration
                    end = contentsLength;
                }
                String substring = contents.substring(start, end);
                System.out.println("start: " + start + "end" + end);
                helpers[i] = new Thread(new FrequencyCounter(frequency, substring));
                helpers[i].setName("hey_" + i);
                helpers[i].start();
            }
            for (Thread t : helpers) {
                t.join();
            }
            System.out.println(frequency.toString());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

class FrequencyCounter implements Runnable {
    
    Map<Character, Integer> frequency;
    String text;

    public FrequencyCounter(Map<Character, Integer> frequency, String text) {
        this.frequency = frequency;
        this.text = text;
    }

    @Override
    public void run() {
        for (int k = 0; k < text.length(); k++) {
            char key = text.charAt(k);
            if (frequency.containsKey(key)) {
                frequency.replace(key, frequency.get(key) + 1);
            } else {
                frequency.put(key, 1);
            }
        }
    }

}
