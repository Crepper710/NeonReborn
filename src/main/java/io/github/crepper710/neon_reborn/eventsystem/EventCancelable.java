package io.github.crepper710.neon_reborn.eventsystem;

public class EventCancelable extends Event {

    private boolean canceled = false;
    private boolean everBeenCanceled = false;

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
        everBeenCanceled = everBeenCanceled | canceled;
    }

    public void cancel() {
        setCanceled(true);
    }

    public boolean isCanceled() {
        return canceled;
    }

    public boolean hasEverBeenCanceled() {
        return everBeenCanceled;
    }
}
