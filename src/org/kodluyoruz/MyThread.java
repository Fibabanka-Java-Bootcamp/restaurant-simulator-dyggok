package org.kodluyoruz;

public class MyThread extends Thread{
    StateOftheRestaurant stateOftheRestaurant;
    public MyThread(String name, StateOftheRestaurant stateOftheRestaurant){
        super(name);
        this.stateOftheRestaurant = stateOftheRestaurant;
    }
    @Override
    public void run() {
        if (this.getName().equals("customer")) {
            for (int i = 1; i <= 6; i++) {
                if (i % 6 != 0) {
                    stateOftheRestaurant.customerIsComing(i);
                } else {
                    try {
                        Thread.sleep(5000);
                        i = 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        else if(this.getName().equals("waiter")) {

            for (int j = 1; j <= 3; j++) {
                stateOftheRestaurant.Order(j);
                if(j%3==0)j=0;
            }
        }

        else{
            for(int k=1; k<=2; k++){
                stateOftheRestaurant.chefIsPreparing(k);
                if (k%2 ==0){
                    k=0;

                }
            }
        }

    }


}
