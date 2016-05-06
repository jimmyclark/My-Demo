package com.clark.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clark.demo.R;

/**
 * Created by ClarkWu on 2016/3/29.
 */
public class F1_right_Fragment extends Fragment {
    private static final String F1_RIGHT_FRAGMENT_LOG = "F1_right_Fragment";

    @Override
    public void onAttach(Context context) {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onAttach()");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onCreateView()");
        return inflater.inflate(R.layout.fragment1_right,container,false);
    }

    @Override
    public void onStart() {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(F1_RIGHT_FRAGMENT_LOG, "onDetach()");
        super.onDetach();
    }
}
