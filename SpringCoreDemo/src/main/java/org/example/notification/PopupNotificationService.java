package org.example.notification;

public class PopupNotificationService implements NotificationService {

    @Override
    public void sentNotification() {
        System.out.println("Popup Notification sent !");
    }
}
