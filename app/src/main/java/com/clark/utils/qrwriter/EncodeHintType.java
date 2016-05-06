package com.clark.utils.qrwriter;

/**
 * Created by ClarkWu on 2016/3/23.
 */
public enum EncodeHintType {
    ERROR_CORRECTION,
    CHARACTER_SET,
    DATA_MATRIX_SHAPE,
    /** @deprecated */
    @Deprecated
    MIN_SIZE,
    /** @deprecated */
    @Deprecated
    MAX_SIZE,
    MARGIN,
    PDF417_COMPACT,
    PDF417_COMPACTION,
    PDF417_DIMENSIONS,
    AZTEC_LAYERS;

    private EncodeHintType() {
    }
}

