package com.clark.utils.qrreader;

/**
 * Created by ClarkWu on 2016/3/28.
 */
public final class MathUtils {

    private MathUtils() {
    }

    /**
     * Ends up being a bit faster than {@link Math#round(float)}. This merely rounds its
     * argument to the nearest int, where x.5 rounds up to x+1. Semantics of this shortcut
     * differ slightly from {@link Math#round(float)} in that half rounds down for negative
     * values. -2.5 rounds to -3, not -2. For purposes here it makes no difference.
     *
     * @param d real value to round
     * @return nearest {@code int}
     */
    public static int round(float d) {
        return (int) (d + (d < 0.0f ? -0.5f : 0.5f));
    }

    public static float distance(float aX, float aY, float bX, float bY) {
        float xDiff = aX - bX;
        float yDiff = aY - bY;
        return (float) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public static float distance(int aX, int aY, int bX, int bY) {
        int xDiff = aX - bX;
        int yDiff = aY - bY;
        return (float) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

}
