package com.clark.gldemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/5/7.
 */
public class FirstGLActivity extends Activity {
    MyTDView mView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mView = new MyTDView(this);
        mView.requestFocus();
        mView.setFocusableInTouchMode(true);
        setContentView(mView);
    }

    public void onResume(){
        super.onResume();
        mView.onResume();
    }

    public void onPause(){
        super.onPause();
        mView.onPause();
    }
}
