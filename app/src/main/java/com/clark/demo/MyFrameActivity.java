package com.clark.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.clark.fragment.F1_left_Fragment;
import com.clark.fragment.F2_Fragment;
import com.clark.fragment.F3_Fragment;

import java.util.ArrayList;

/**
 * Created by ClarkWu on 2016/3/29.
 */
public class MyFrameActivity extends FragmentActivity {
    private F1_left_Fragment f1_left_fragment;
    private F2_Fragment f2_fragment;
    private F3_Fragment f3_fragment;
    private Button f1_btn;
    private Button f2_btn;
    private Button f3_btn;

    private ArrayList<Integer> fragments = new ArrayList<Integer>();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("MyFrameActivity", "onBackPressed");
        if(fragments.size() > 0 ){
            if(fragments.size() - 2 < 0 ){
                fragments.remove(0);
                return;
            }
            int index = fragments.get(fragments.size() - 2);
            Log.i("MyFrameActivity", "onBackPressed" + index);

            switch(index){
                case 1:
                    f1_btn.setEnabled(false);
                    f2_btn.setEnabled(true);
                    f3_btn.setEnabled(true);
                    break;
                case 2:
                    f1_btn.setEnabled(true);
                    f2_btn.setEnabled(false);
                    f3_btn.setEnabled(true);
                    break;
                case 3:
                    f1_btn.setEnabled(true);
                    f2_btn.setEnabled(true);
                    f3_btn.setEnabled(false);
                    break;
            }

            fragments.remove(fragments.size()-1);
        }
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        if(findViewById(R.id.fragment_center) != null) {
            if (savedInstanceState != null) return;

            f1_left_fragment = new F1_left_Fragment();
            f2_fragment = new F2_Fragment();

            Bundle bundle = new Bundle();
            bundle.putString("f2_params","f2_arguments");
            f2_fragment.setArguments(bundle);

            f3_fragment = new F3_Fragment();

            f1_btn = (Button) findViewById(R.id.f1);
            f2_btn = (Button) findViewById(R.id.f2);
            f3_btn = (Button) findViewById(R.id.f3);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_center, f1_left_fragment).commit();
            fragments.add(1);
            f1_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    f1_btn.setEnabled(false);
                    f2_btn.setEnabled(true);
                    f3_btn.setEnabled(true);
                    fragments.add(1);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack("f1_btn");
                    fragmentTransaction.replace(R.id.fragment_center,f1_left_fragment).commit();
                }
            });

            f2_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    f1_btn.setEnabled(true);
                    f2_btn.setEnabled(false);
                    f3_btn.setEnabled(true);
                    fragments.add(2);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack("f2_btn");
                    fragmentTransaction.replace(R.id.fragment_center,f2_fragment).commit();
                }
            });

            f3_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    f1_btn.setEnabled(true);
                    f2_btn.setEnabled(true);
                    f3_btn.setEnabled(false);
                    fragments.add(3);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack("f3_btn");
                    fragmentTransaction.replace(R.id.fragment_center, f3_fragment).commit();
                }
            });

            f1_btn.setEnabled(false);
            f2_btn.setEnabled(true);
            f3_btn.setEnabled(true);

        }


    }
}
