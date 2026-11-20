package org.example;

import org.example.notification.NotificationService;

public class OrderService {
    NotificationService notification ;
       public OrderService( NotificationService notification){
            this.notification=notification;
        }
        public OrderService(){

        }

        void placedOrder(){
            System.out.println("Order placed!");
             notification.sentNotification();
        }

    public void setNotification(NotificationService notification) {
        this.notification = notification;
    }
}
