package io.github.crepper710.neon_reborn.notifications;

public enum NotificationPriority {

    LOW(-1), DEFAULT(0), HIGH(1);

    private final int value;

    NotificationPriority (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
