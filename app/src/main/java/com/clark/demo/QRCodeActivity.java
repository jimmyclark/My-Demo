package com.clark.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.clark.utils.QRUtils;

/**
 * Created by ClarkWu on 2016/3/25.
 */
public class QRCodeActivity extends Activity {
    private EditText qrcode_Edit;
    private ImageView qrcode_img;
    private Button qrcode_showBtn;
    private Button qrcode_readBtn;
    private TextView showTextView;
    private View splitView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_main);

        qrcode_Edit = (EditText) findViewById(R.id.qrcode_edit);
        qrcode_img = (ImageView) findViewById(R.id.qrcode_img);
        qrcode_showBtn = (Button) findViewById(R.id.show_qrcode);
        qrcode_readBtn = (Button) findViewById(R.id.read_qrcode);
        showTextView = (TextView) findViewById(R.id.showText);
        splitView = (View)findViewById(R.id.split);

        qrcode_img.setVisibility(View.INVISIBLE);
        qrcode_readBtn.setEnabled(false);
        qrcode_showBtn.setEnabled(false);

        qrcode_Edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                qrcode_readBtn.setEnabled(false);
                qrcode_showBtn.setEnabled(false);
                qrcode_img.setVisibility(View.INVISIBLE);
                showTextView.setText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (qrcode_Edit.getText().toString().trim().length() > 0) {
                    qrcode_showBtn.setEnabled(true);
                } else {
                    qrcode_showBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        qrcode_showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qrcode_Edit.getText().toString().trim().length() > 0) {
                    String qrcodeEdit = qrcode_Edit.getText().toString();
                    if (!qrcodeEdit.startsWith("http://")) {
                        qrcodeEdit = "http://" + qrcodeEdit;
                    }

                    //显示二维码
                    Bitmap bitmap = QRUtils.getInstance().createBitmap(qrcodeEdit, 0, 0);
                    if (bitmap == null) {
                        Toast.makeText(QRCodeActivity.this, "二维码生成失败!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(QRCodeActivity.this, "二维码生成成功!", Toast.LENGTH_LONG).show();
                    }
                    qrcode_img.setImageBitmap(bitmap);
                    qrcode_img.setVisibility(View.VISIBLE);
                    qrcode_readBtn.setEnabled(true);
                } else {
                    qrcode_readBtn.setEnabled(false);
                    qrcode_showBtn.setEnabled(false);
                    qrcode_img.setVisibility(View.INVISIBLE);
                }
            }
        });

        qrcode_readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splitView.setVisibility(View.VISIBLE);
                TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,qrcode_img.getHeight());
                translateAnimation.setDuration(2000);
                splitView.setAnimation(translateAnimation);
                translateAnimation.startNow();
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        String qrcode_url = QRUtils.getInstance().readQRImage(qrcode_img);
                        Toast.makeText(QRCodeActivity.this,"二维码" + qrcode_url ,Toast.LENGTH_LONG).show();
                        if(qrcode_url != null && qrcode_url.trim().length() > 0){
                            showTextView.setText(qrcode_url);
                        }
                        splitView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
//
            }
        });

        qrcode_img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String qrcode_url = QRUtils.getInstance().readQRImage(qrcode_img);
                if(qrcode_url != null && qrcode_url.trim().length() > 0){
                    showTextView.setText(qrcode_url);
                    //浏览器跳转
                    Uri newUri = Uri.parse(qrcode_url);
                    Intent intent = new Intent(Intent.ACTION_VIEW,newUri);
                    startActivity(intent);

                }

                return false;
            }
        });

    }


}
