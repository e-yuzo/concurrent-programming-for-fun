package threads.task_03.ex_04;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuzo Description: Implemente uma solução para o problema do Barbeiro
 * Dorminhoco usando monitores.
 */
public class Main {

    public static void main(String a[]) {
        BarberShopMonitor shop = new BarberShopMonitor();

        Thread threadBarber = new Thread(new Barber(shop));
        Thread threadCustomers = new Thread(new CustomerGenerator(shop));

        threadCustomers.start();
        threadBarber.start();
    }

}

class Barber implements Runnable {

    BarberShopMonitor shop;

    public Barber(BarberShopMonitor shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            try {
                shop.cutHair();
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 4000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Barber.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class Customer implements Runnable {

    BarberShopMonitor shop;

    public Customer(BarberShopMonitor shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        getHairCut();
    }

    private synchronized void getHairCut() {
        shop.grabSeat();
    }

}

class CustomerGenerator implements Runnable {

    BarberShopMonitor shop;

    public CustomerGenerator(BarberShopMonitor shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread customer = new Thread(new Customer(shop));
                customer.start();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException ex) {
                Logger.getLogger(CustomerGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class BarberShopMonitor {

    Semaphore customers = new Semaphore(0);
    Semaphore barber = new Semaphore(0);
    Semaphore cutting = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);
    int customer = 0;

    public BarberShopMonitor() {
        
    }

    public void cutHair() {
        try {
            System.out.println("Waiting for customers.");
            customers.acquire();
            mutex.acquire();
            System.out.println("Barber found a customer in line.");
            customer--;
            mutex.release();
            System.out.println("Getting ready to cut someone's hair.");
            barber.release();
            System.out.println("Barber cuting hair.");
            cutting.acquire();
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            System.out.println("Finished cuting hair.");
        } catch (InterruptedException ex) {
            Logger.getLogger(BarberShopMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void grabSeat() {
        try {
            mutex.acquire();
            if (customer < 3) {
                System.out.println("You got a seat. ENTERED shop.");
                customer++;
                customers.release();
                mutex.release();
                System.out.println("WAITING for barber.");
                barber.acquire();
                System.out.println("Customer getting hair cut.");
                cutting.release();
            } else {
                System.out.println("The seats are occupied. LEAVE");
                mutex.release();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(BarberShopMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
