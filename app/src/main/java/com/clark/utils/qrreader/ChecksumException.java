package com.clark.utils.qrreader;

import com.clark.utils.qrwriter.ReaderException;

/**
 * Created by ClarkWu on 2016/3/28.
 */
public final class ChecksumException extends ReaderException {

    private static final ChecksumException INSTANCE = new ChecksumException();
    static {
        INSTANCE.setStackTrace(NO_TRACE); // since it's meaningless
    }

    public static ChecksumException getChecksumInstance() {
        return isStackTrace ? new ChecksumException() : INSTANCE;
    }

    public static ChecksumException getChecksumInstance(Throwable cause) {
        return isStackTrace ? new ChecksumException() : INSTANCE;
    }
}
