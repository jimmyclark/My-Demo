package com.clark.utils.qrwriter;

/**
 * Created by ClarkWu on 2016/3/23.
 */
final class BlockPair {
    private final byte[] dataBytes;
    private final byte[] errorCorrectionBytes;

    BlockPair(byte[] data, byte[] errorCorrection) {
        this.dataBytes = data;
        this.errorCorrectionBytes = errorCorrection;
    }

    public byte[] getDataBytes() {
        return this.dataBytes;
    }

    public byte[] getErrorCorrectionBytes() {
        return this.errorCorrectionBytes;
    }
}
