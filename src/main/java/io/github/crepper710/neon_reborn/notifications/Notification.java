package io.github.crepper710.neon_reborn.notifications;

public class Notification {

    private final String title;
    private final String message;
    private final NotificationPriority priority;
    private final float screenTime;

    public Notification(String title, String message, NotificationPriority priority, float screenTime) {
        this.title = title;
        this.message = message;
        this.priority = priority;
        this.screenTime = screenTime;
    }

    public Notification(String title, String message, NotificationPriority priority) {
        this(title, message, priority, 5.0f);
    }

    public Notification(String title, String message, float screenTime) {
        this(title, message, NotificationPriority.DEFAULT, screenTime);
    }

    public String getMessage() {
        return message;
    }

    public NotificationPriority getPriority() {
        return priority;
    }

    public float getScreenTime() {
        return screenTime;
    }

    public String getTitle() {
        return title;
    }

}
