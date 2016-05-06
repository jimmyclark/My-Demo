package com.clark.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.clark.utils.qrreader.BinaryBitmap;
import com.clark.utils.qrreader.DecodeHintType;
import com.clark.utils.qrreader.HybirdBinarizer;
import com.clark.utils.qrreader.QRCodeReader;
import com.clark.utils.qrreader.RGBLuminanceSource;
import com.clark.utils.qrreader.Result;
import com.clark.utils.qrwriter.BarcodeFormat;
import com.clark.utils.qrwriter.BitMatrix;
import com.clark.utils.qrwriter.MultiFormatWriter;
import com.clark.utils.qrwriter.WriterException;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by ClarkWu on 2016/3/28.
 */
public class QRUtils {
    private static final int QR_WIDTH = 300;
    private static final int QR_HEIGHT = 300;
    private static QRUtils qrUtils;
    private QRUtils(){}
    public static QRUtils getInstance(){
        if(qrUtils == null){
            qrUtils = new QRUtils();
        }
        return qrUtils;
    }

    public Bitmap createBitmap(String str,int qrwidth,int qrheight){
        try {
            if(qrwidth == 0 || qrheight == 0) {
                qrwidth = QR_WIDTH;
                qrheight = QR_HEIGHT;
            }
            BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, qrwidth, qrheight);
            int[] pixels = new int[qrwidth*qrheight];
            for(int y = 0; y<qrwidth; ++y){
                for(int x = 0; x<qrheight; ++x){
                    if(matrix.get(x, y)){
                        pixels[y*qrwidth+x] = 0xff000000; // black pixel
                    } else {
                        pixels[y*qrwidth+x] = 0x00ffffff; // white pixel
                    }
                }
            }
            Bitmap bmp = Bitmap.createBitmap(qrwidth, qrheight, Bitmap.Config.ARGB_8888);
            bmp.setPixels(pixels, 0, qrwidth, 0, 0, qrwidth, qrheight);
            return bmp;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readQRImage(ImageView imageView){
        String content = null;
        Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

        // 获得待解析的图片
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybirdBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        try {
            Result result = reader.decode(bitmap1, hints);
            // 得到解析后的文字
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
