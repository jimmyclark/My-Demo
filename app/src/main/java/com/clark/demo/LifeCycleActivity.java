package com.clark.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by ClarkWu on 2016/3/25.
 */
public class LifeCycleActivity extends Activity {
    private static final String LIFE_LOG = "LifeCycleActivity";


    public void onCreate(Bundle savedInstanceState){
        Log.i(LIFE_LOG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifecycle);
        //这里如果直接调用finish，应用会走destroy而不走其他生命周期函数。
//        finish();

        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LIFE_LOG,"finish onClick");
                finish();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            Log.i(LIFE_LOG,"Life Log" + savedInstanceState.getInt(LIFE_LOG));
        }
        Log.i(LIFE_LOG,"onRestoreInstanceState()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LIFE_LOG, "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LIFE_LOG, "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LIFE_LOG, "onRestart()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(LIFE_LOG, "onSaveInstanceState()");
        outState.putInt(LIFE_LOG,200);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LIFE_LOG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LIFE_LOG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LIFE_LOG, "onDestroy()");
    }
}
