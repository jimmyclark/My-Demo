package com.clark.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.clark.demo.R;

/**
 * Created by ClarkWu on 2016/3/29.
 */
public class F1_left_Fragment extends Fragment {
    private static final String F1_LEFT_FRAGMENT_LOG = "F1_left_Fragment";

    private Button firstPage;
    private Button secondPage;
    private Button thirdPage;

    private F1_right_Fragment f1_rightFragment;
    private F1_right_Fragment2 f1_right_fragment2;
    private F1_right_Fragment3 f1_right_fragment3;

    @Override
    public void onAttach(Context context) {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onAttach()");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onCreateView()");
        View view = inflater.inflate(R.layout.fragment1_left,container,false);
        firstPage = (Button) view.findViewById(R.id.firstPage);
        secondPage = (Button) view.findViewById(R.id.secondPage);
        thirdPage = (Button) view.findViewById(R.id.thirdpage);

        f1_rightFragment = new F1_right_Fragment();
        f1_right_fragment2 = new F1_right_Fragment2();
        f1_right_fragment3 = new F1_right_Fragment3();

        getChildFragmentManager().beginTransaction().replace(R.id.left_framecenter,f1_rightFragment).commit();
        firstPage.setEnabled(false);
        secondPage.setEnabled(true);
        thirdPage.setEnabled(true);

        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.left_framecenter,f1_rightFragment).commit();
                firstPage.setEnabled(false);
                secondPage.setEnabled(true);
                thirdPage.setEnabled(true);
            }
        });

        secondPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.left_framecenter,f1_right_fragment2).commit();
                firstPage.setEnabled(true);
                secondPage.setEnabled(false);
                thirdPage.setEnabled(true);
            }
        });

        thirdPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.left_framecenter,f1_right_fragment3).commit();
                firstPage.setEnabled(true);
                secondPage.setEnabled(true);
                thirdPage.setEnabled(false);
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(F1_LEFT_FRAGMENT_LOG, "onDetach()");
        super.onDetach();
    }
}
