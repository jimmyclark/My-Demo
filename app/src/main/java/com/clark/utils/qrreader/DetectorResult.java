package com.clark.utils.qrreader;

import com.clark.utils.qrwriter.BitMatrix;

/**
 * Created by ClarkWu on 2016/3/28.
 */
public class DetectorResult {

    private final BitMatrix bits;
    private final ResultPoint[] points;

    public DetectorResult(BitMatrix bits, ResultPoint[] points) {
        this.bits = bits;
        this.points = points;
    }

    public final BitMatrix getBits() {
        return bits;
    }

    public final ResultPoint[] getPoints() {
        return points;
    }

}