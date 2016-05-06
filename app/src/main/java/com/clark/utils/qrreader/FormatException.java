package com.clark.utils.qrreader;

import com.clark.utils.qrwriter.ReaderException;

/**
 * Created by ClarkWu on 2016/3/28.
 */
public final class FormatException extends ReaderException {

    private static final FormatException INSTANCE = new FormatException();
    static {
        INSTANCE.setStackTrace(NO_TRACE); // since it's meaningless
    }

    private FormatException() {
    }

    public static FormatException getFormatInstance() {
        return isStackTrace ? new FormatException() : INSTANCE;
    }

    public static FormatException getFormatInstance(Throwable cause) {
        return isStackTrace ? new FormatException() : INSTANCE;
    }
}
