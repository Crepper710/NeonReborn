package io.github.crepper710.neon_reborn;

import io.github.crepper710.neon_reborn.eventsystem.EventManager;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePack;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePackingInputStream;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.BytePackingOutputStream;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes.BytePackDouble;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes.BytePackList;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes.BytePackMap;
import io.github.crepper710.neon_reborn.filesystem.bytepacking.datatypes.BytePackStringUTF8;
import io.github.crepper710.neon_reborn.gui.SettingsGui;
import io.github.crepper710.neon_reborn.notifications.NotificationManager;
import io.github.crepper710.neon_reborn.settingsystem.SettingManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeonReborn {

    private static NeonReborn instance;

    private Logger logger;
    private EventManager eventManager;
    private NotificationManager notificationManager;
    private SettingManager settingManager;
    private SettingsGui setingsGui;

    public static class Info {

        public static final String SHORT_NAME = "Neon";
        public static final String SHORT_NAME_SUFFIX = "Reborn";
        public static final String NAME = "Neon Reborn";
        public static final String VERSION = "1.0.0";
        public static final String AUTHOR = "Crepper710";

    }

    public static void start() {
        instance = new NeonReborn();
        instance.init();
        instance.getLogger().info("start up");
    }

    public static void stop() {
        instance.getLogger().info("shut down");
        instance = null;
    }

    private NeonReborn() {}

    private void init() {
        logger = LogManager.getLogger("Neon Reborn", new StringFormatterMessageFactory());
        eventManager = new EventManager();
        notificationManager = new NotificationManager();
        settingManager = new SettingManager();
        setingsGui = new SettingsGui();
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        BytePackMap bp = new BytePackMap();
        bp.setInteger("int", 1);
        bp.setStringASCII("string1", "ascii");
        bp.setStringUTF16("string2", "utf16");
        bp.set("list", new BytePackList(Arrays.asList(new BytePackDouble(0.123), new BytePackStringUTF8("test"), new BytePackMap())));
        try {
            new BytePackingOutputStream(bais).writeBytePack(bp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        NeonReborn.getInstance().getLogger().info(bais.toString() + " : " + IntStream.range(0, bais.toByteArray().length).mapToObj(i -> String.format("%02x", bais.toByteArray()[i])).collect(Collectors.joining(" ")));
        try {
            BytePack re = new BytePackingInputStream(new ByteArrayInputStream(bais.toByteArray())).readBytePack();
            NeonReborn.getInstance().getLogger().info(re.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public SettingManager getSettingManager() {
        return settingManager;
    }

    public static NeonReborn getInstance() {
        return instance;
    }

    @net.minecraftforge.fml.common.Mod(modid = "neon_reborn", version = "1.0.0")
    public static class Mod {

    }

}