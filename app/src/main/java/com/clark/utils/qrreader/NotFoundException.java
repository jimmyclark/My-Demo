package com.clark.utils.qrreader;

import com.clark.utils.qrwriter.ReaderException;

/**
 * Created by ClarkWu on 2016/3/28.
 */
public final class NotFoundException extends ReaderException {

    private static final NotFoundException INSTANCE = new NotFoundException();
    static {
        INSTANCE.setStackTrace(NO_TRACE); // since it's meaningless
    }

    public static NotFoundException getNotFoundInstance() {
        return INSTANCE;
    }

}