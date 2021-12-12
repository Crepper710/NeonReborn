package io.github.crepper710.neon_reborn.utils;

import scala.actors.threadpool.Arrays;

public class StackUtils {

    public static StackTraceElement[] getCurrentStack() {
        final StackTraceElement[] result = new Exception().getStackTrace();
        return (StackTraceElement[]) Arrays.copyOfRange(result, 1, result.length);
    }

}
