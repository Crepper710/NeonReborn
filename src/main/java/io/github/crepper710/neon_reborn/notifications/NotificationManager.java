package io.github.crepper710.neon_reborn.notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private final List<Notification> displayNotifications;
    private final List<Notification> notifications;

    public NotificationManager() {
        this.displayNotifications = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public void addNotification(Notification notification) {
        synchronized (this) {
            if (notification.getPriority().getValue() >= 0) {
                displayNotifications.add(notification);
            }
            notifications.add(notification);
        }
    }

    public Notification getNextDisplayNotification() {
        synchronized (this) {
            if (displayNotifications.size() >= 1) {
                return displayNotifications.remove(0);
            }
        }
        throw new RuntimeException("no message to get");
    }

    public boolean hasNextDisplayNotification() {
        synchronized (this) {
            return displayNotifications.size() >= 1;
        }
    }

    public List<Notification> getDisplayNotifications() {
        return displayNotifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
}
