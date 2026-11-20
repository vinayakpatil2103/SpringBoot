package org.example.notification;

public class DummyEmailSend implements NotificationService{
    @Override
    public void sentNotification() {
        System.out.println("Dummy Email  Notification sent !");
    }
}
