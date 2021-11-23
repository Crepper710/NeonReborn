package io.github.crepper710.neon_reborn.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.AbstractLogger;

public class WrappedLogger extends Logger {

    private final Logger parent;
    private final String prefix;

    private WrappedLogger (Logger parent, String prefix) {
        super(parent.getContext(), parent.getName(), parent.getMessageFactory());
        this.parent = parent;
        this.prefix = prefix;
    }

    @Override
    public void log(Marker marker, String fqcn, Level level, Message data, Throwable t) {
        parent.log(marker, fqcn, level, new Message() {
            @Override
            public String getFormattedMessage() {
                return prefix + data.getFormattedMessage();
            }

            @Override
            public String getFormat() {
                return prefix + data.getFormat();
            }

            @Override
            public Object[] getParameters() {
                return data.getParameters();
            }

            @Override
            public Throwable getThrowable() {
                return data.getThrowable();
            }
        }, t);
    }

    public static org.apache.logging.log4j.Logger wrapIfPossible(org.apache.logging.log4j.Logger toWrap, String prefix) {
        if (!(toWrap instanceof Logger)) {
            return toWrap;
        }
        return new WrappedLogger((Logger) toWrap, prefix);
    }
}
