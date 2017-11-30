package com.jlkf.jdsaleside.other.base;

/**
 * Created by DuoNuo on 2017/9/21.
 */

/**
 * 自己定义的类用来处理release的奔溃信息
 */
public final class FakeCrashLibrary {
    public static void log(int priority, String tag, String message) {
        // TODO add log entry to circular buffer.
    }

    public static void logWarning(Throwable t) {
        // TODO report non-fatal warning.
    }

    public static void logError(Throwable t) {
        // TODO report non-fatal error.
    }

    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }
}