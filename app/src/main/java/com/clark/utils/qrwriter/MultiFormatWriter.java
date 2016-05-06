package com.clark.utils.qrwriter;

import java.util.Map;

/**
 * Created by ClarkWu on 2016/3/23.
 */
public class MultiFormatWriter implements Writer {
    @Override
    public BitMatrix encode(String contents,
                            BarcodeFormat format,
                            int width,
                            int height) throws WriterException {
        return encode(contents, format, width, height, null);
    }

    @Override
    public BitMatrix encode(String contents,
                            BarcodeFormat format,
                            int width, int height,
                            Map<EncodeHintType,?> hints) throws WriterException {

        Writer writer;
        switch (format) {
            case QR_CODE:
                writer = new QRCodeWriter();
                break;

            default:
                throw new IllegalArgumentException("No encoder available for format " + format);
        }
        return writer.encode(contents, format, width, height, hints);
    }
}
