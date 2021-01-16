package org.kodluyoruz;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        StateOftheRestaurant stateOftheRestaurant = new StateOftheRestaurant();
        MyThread customers = new MyThread("customer",stateOftheRestaurant);
        MyThread waiters = new MyThread("waiter",stateOftheRestaurant);
        MyThread chefs = new MyThread("chef",stateOftheRestaurant);

        customers.start();
        waiters.start();
        chefs.start();

        customers.join();
        waiters.join();
        chefs.join();
    }
}
