package com.clark.utils.qrwriter;

import java.util.Map;

/**
 * Created by ClarkWu on 2016/3/23.
 */
public interface Writer {
    BitMatrix encode(String var1, BarcodeFormat var2, int var3, int var4) throws WriterException;

    BitMatrix encode(String var1, BarcodeFormat var2, int var3, int var4, Map<EncodeHintType, ?> var5) throws WriterException;
}
