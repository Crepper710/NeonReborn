package io.github.crepper710.neon_reborn.eventsystem;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    Class<? extends Event>[] value();

    EventPriority priority() default EventPriority.DEFAULT;

}
