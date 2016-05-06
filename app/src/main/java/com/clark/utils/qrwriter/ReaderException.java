package com.clark.utils.qrwriter;

/**
 * Created by ClarkWu on 2016/3/23.
 */
public abstract class ReaderException extends Exception {

    // disable stack traces when not running inside test units
    protected static final boolean isStackTrace =
            System.getProperty("surefire.test.class.path") != null;
    protected static final StackTraceElement[] NO_TRACE = new StackTraceElement[0];

    // Prevent stack traces from being taken
    // srowen says: huh, my IDE is saying this is not an override. native methods can't be overridden?
    // This, at least, does not hurt. Because we use a singleton pattern here, it doesn't matter anyhow.
    @Override
    public final Throwable fillInStackTrace() {
        return null;
    }

}