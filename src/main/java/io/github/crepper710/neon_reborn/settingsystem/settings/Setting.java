package io.github.crepper710.neon_reborn.settingsystem.settings;

import io.github.crepper710.neon_reborn.NeonReborn;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class Setting<T> {

    private T value;
    private final Consumer<T> onUpdate;
    private final BooleanSupplier shouldDisplay;

    public Setting(T defaultValue, Consumer<T> onUpdate, BooleanSupplier shouldDisplay) {
        this.value = defaultValue;
        this.onUpdate = onUpdate;
        this.shouldDisplay = shouldDisplay;
    }

    public Setting(T defaultValue, BooleanSupplier shouldDisplay) {
        this(defaultValue, (t) -> {}, shouldDisplay);
    }

    public Setting(T defaultValue, Consumer<T> onUpdate) {
        this(defaultValue, onUpdate, () -> true);
    }

    public <U>Setting(T defaultValue, Consumer<T> onUpdate, Setting<U> setting, U[] possibleValues) {
        this(defaultValue, onUpdate, () -> {
            for (U u : possibleValues) {
                if (u != null) {
                    if (u.equals(setting.getValue())) {
                        return true;
                    }
                }
            }
            return false;
        });
    }

    public <U>Setting(T defaultValue, Setting<U> setting, U[] possibleValues) {
        this(defaultValue, (t) -> {}, () -> {
            for (U u : possibleValues) {
                if (u != null) {
                    if (u.equals(setting.getValue())) {
                        return true;
                    }
                }
            }
            return false;
        });
    }

    public Setting(T defaultValue) {
        this(defaultValue, (t) -> {}, () -> true);
    }

    public T getValue() {
        return value;
    }

    public boolean shouldDisplay() {
        try {
            return shouldDisplay.getAsBoolean();
        } catch (Exception e) {
            NeonReborn.getInstance().getLogger().error("Could not get info displayability of the current setting:", e);
        }
        return false;
    }

    public void setValue(T value) {
        block: {
            if (value == null && this.value == null) {
                break block;
            }
            if (value != null && this.value != null) {
                if (this.value.equals(value)) {
                    break block;
                }
            }
            try {
                onUpdate.accept(value);
            } catch (Exception e) {
                NeonReborn.getInstance().getLogger().error("Error while executing onUpdate of setting:", e);
            }
        }
        this.value = value;
    }

}
