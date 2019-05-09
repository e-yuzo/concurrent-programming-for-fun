/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.monitor.second;

/**
 *
 * @author yuzo Description:
 */
public class Counter {

    //private int offset = 0;
    //private boolean recorded = true;
    //String[] buffer = new String[6];
    //private int removeIndex = 0;
    //private int addIndex = 0;
    private int x = 0;
//    private List<String> buffer = new ArrayList<>();
    //String record;

//    public void clean() {
//        buffer = new ArrayList<>();
//        offset = 0;
//    }
    public synchronized void increment() {
        x++;
        notifyAll();
    }

    public synchronized void sleepUntil(int sleep) {
        while (x < sleep) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
    }

//    public synchronized void addNews(String news) {
//        while (addIndex - removeIndex == buffer.length) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.out.println("Thread interrupted: " + e);
//            }
//        }
//        //recorded = false;
//        buffer[(addIndex++ % buffer.length)] = news;
//        //record = news;
//        notifyAll();
////        buffer.add(news);
//    }
//
//    public synchronized String getLastNews() {
//        while (addIndex == removeIndex) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.out.println("Thread interrupted: " + e);
//            }
//        }
//        //recorded = true;
//        notifyAll();
//        return buffer[removeIndex++ % 6];
////        int bufferSize = buffer.size();
////        if (offset < bufferSize - 1) {
////            return buffer.get(offset++);
////        }
////        return null;
//    }
}
