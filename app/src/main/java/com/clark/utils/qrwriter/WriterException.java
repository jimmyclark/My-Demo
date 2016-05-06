package com.clark.utils.qrwriter;

/**
 * Created by ClarkWu on 2016/3/23.
 */
public final class WriterException extends Exception {
    public WriterException() {
    }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(Throwable cause) {
        super(cause);
    }
}