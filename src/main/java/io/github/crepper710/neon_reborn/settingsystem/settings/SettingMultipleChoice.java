package io.github.crepper710.neon_reborn.settingsystem.settings;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class SettingMultipleChoice<T> extends Setting<T> {

    public final T[] possibleSettings;

    public SettingMultipleChoice(T defaultValue, T[] possibleSettings, Consumer<T> onUpdate, BooleanSupplier shouldDisplay) {
        super(defaultValue, onUpdate, shouldDisplay);
        this.possibleSettings = possibleSettings;
    }

    public SettingMultipleChoice(T defaultValue, T[] possibleSettings, BooleanSupplier shouldDisplay) {
        this(defaultValue, possibleSettings, (t) -> {}, shouldDisplay);
    }

    public SettingMultipleChoice(T defaultValue, T[] possibleSettings, Consumer<T> onUpdate) {
        this(defaultValue, possibleSettings, onUpdate, () -> true);
    }

    public <U>SettingMultipleChoice(T defaultValue, T[] possibleSettings, Consumer<T> onUpdate, Setting<U> setting, U[] possibleValues) {
        this(defaultValue, possibleSettings, onUpdate, () -> {
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

    public <U>SettingMultipleChoice(T defaultValue, T[] possibleSettings, Setting<U> setting, U[] possibleValues) {
        this(defaultValue, possibleSettings, (t) -> {}, () -> {
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

    public SettingMultipleChoice(T defaultValue, T[] possibleSettings) {
        this(defaultValue, possibleSettings, (t) -> {}, () -> true);
    }

    public boolean isPossibleValue(T value) {
        for (T possibleSetting : possibleSettings) {
            if (possibleSetting.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValue(T value) {
        if (!isPossibleValue(value)) {
            throw new IllegalArgumentException("value has to be present in possibleSettings");
        }
        super.setValue(value);
    }

    public T[] getPossibleSettings() {
        return possibleSettings.clone();
    }
}
