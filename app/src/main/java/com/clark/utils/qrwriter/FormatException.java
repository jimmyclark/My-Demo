package com.clark.utils.qrwriter;

/**
 * Created by ClarkWu on 2016/3/23.
 */
public final class FormatException extends ReaderException {
    private static final FormatException INSTANCE = new FormatException();

    private FormatException() {
    }

    public static FormatException getFormatInstance() {
        return isStackTrace?new FormatException():INSTANCE;
    }

    public static FormatException getFormatInstance(Throwable cause) {
        return isStackTrace?new FormatException():INSTANCE;
    }

    static {
        INSTANCE.setStackTrace(NO_TRACE);
    }
}
