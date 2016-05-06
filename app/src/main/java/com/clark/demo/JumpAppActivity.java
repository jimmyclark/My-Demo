package com.clark.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ClarkWu on 2016/5/6.
 */
public class JumpAppActivity extends Activity {
    private EditText needMeesage;
    private Button startInitBtn;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumpapp);

        needMeesage = (EditText) findViewById(R.id.needMeesage);
        startInitBtn = (Button) findViewById(R.id.startInit);

        startInitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = needMeesage.getText().toString();
                String url = "http://clark.app?message=" + message;
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startActivity(intent);
                android.os.Process.killProcess(Process.myPid());
            }
        });
    }
}
