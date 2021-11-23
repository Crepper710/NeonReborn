package io.github.crepper710.neon_reborn.eventsystem;

public enum EventPriority {

    HIGH(1), DEFAULT(0), LOW(-1);

    private final int value;

    EventPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
