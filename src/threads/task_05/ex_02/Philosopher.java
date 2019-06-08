package threads.task_05.ex_02;

import java.util.logging.Level;
import java.util.logging.Logger;


class Philosopher implements Runnable {
    
    int id = 0;
    Thinker fork = null;
    
    public Philosopher(int initId, Thinker initr) {
        id = initId;
        fork = initr;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Phil " + id + " thinking");
                Thread.sleep(30);
                System.out.println("Phil " + id + " hungry");
                fork.pickup(id);
                System.out.println("Phil " + id + " eating");
                Thread.sleep(30);
                fork.drop(id);
            } catch (Exception ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
