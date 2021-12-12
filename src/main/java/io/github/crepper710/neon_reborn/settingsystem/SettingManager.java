package io.github.crepper710.neon_reborn.settingsystem;

import io.github.crepper710.neon_reborn.settingsystem.settings.Setting;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class SettingManager {

    private final TreeMap<String, Setting<?>> settings;

    public SettingManager() {
        settings = new TreeMap<>();
    }

    public Map<String, Setting<?>> getSettings() {
        return Collections.unmodifiableMap(settings);
    }

    public void set(String key, Setting<?> value) {
        settings.put(key, value);
    }

    public Setting<?> delete(String key) {
        return settings.remove(key);
    }

    public Setting<?> get(String key) {
        return settings.get(key);
    }

    public boolean has(String key) {
        return settings.containsKey(key);
    }

}
