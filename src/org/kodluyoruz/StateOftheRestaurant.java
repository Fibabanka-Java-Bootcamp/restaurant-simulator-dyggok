package org.kodluyoruz;

import java.util.Random;

public class StateOftheRestaurant {
    int order=0,l;
    boolean order2=false;
    Random rnd = new Random();
    RestaurantsMenu restaurantsMenu = new RestaurantsMenu();

    synchronized void customerIsComing(int customerId) {
        System.out.println(customerId + ". customer is coming");
        int choice = rnd.nextInt(restaurantsMenu.menu.length);
        for(l=1; l<=choice; l++){
            System.out.println(customerId+". customer wants "
                    +restaurantsMenu.menu[rnd.nextInt(restaurantsMenu.menu.length)]);}
        l=1;
        order = 1;
        notifyAll();

        try {
            while (this.order == 1) {
                wait();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    synchronized void Order(int waiterId) {
        if (order == 0) {
            notifyAll();
            return;
        }
        System.out.println(waiterId + ". waiter received the order");
        order = 0;
        order2 = true;
        notifyAll();

        try {
            while (order == 0) {

                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    synchronized void chefIsPreparing(int chefsId){
        if(order2 == false) {
            notifyAll();
            return;
        }
        System.out.println(chefsId + ". chef is preparing the order");
        System.out.println("The order is ready");
        order2 = false;
        notifyAll();
        while(order2 == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
