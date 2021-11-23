package io.github.crepper710.neon_reborn;

import io.github.crepper710.neon_reborn.eventsystem.EventBus;
import io.github.crepper710.neon_reborn.utils.WrappedLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
import org.slf4j.MDC;

public class NeonReborn {

    private static NeonReborn instance;

    private final Logger logger;
    private final EventBus eventBus;

    public static class Info {

        public static final String SHORT_NAME = "Neon";
        public static final String SHORT_NAME_SUFFIX = "Reborn";
        public static final String NAME = "Neon Reborn";
        public static final String VERSION = "1.0.0";
        public static final String AUTHOR = "Crepper710";

    }

    public static void start() {
        instance = new NeonReborn();
        instance.getLogger().info("start up");
    }

    public static void stop() {
        instance.getLogger().info("shut down");
        instance = null;
    }

    private NeonReborn() {
        logger = WrappedLogger.wrapIfPossible(LogManager.getLogger(NeonReborn.class, new StringFormatterMessageFactory()), "[Neom Reborn] ");
        eventBus = new EventBus();
    }

    public Logger getLogger() {
        return logger;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public static NeonReborn getInstance() {
        return instance;
    }

    @net.minecraftforge.fml.common.Mod(modid = "neon_reborn", version = "1.0.0")
    public static class Mod {

    }

}