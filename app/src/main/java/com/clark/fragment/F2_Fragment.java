package com.clark.fragment;

/**
 * Created by ClarkWu on 2016/3/29.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clark.demo.R;

/**
 * Created by ClarkWu on 2016/3/29.
 */
public class F2_Fragment extends Fragment {
    private static final String F2_Fragment = "F2_Fragment";
    private TextView txtTitleView;

    @Override
    public void onAttach(Context context) {
        Log.i(F2_Fragment, "onAttach()");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(F2_Fragment, "onCreate()");

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        Log.i(F2_Fragment, "onCreateView()");
        txtTitleView = (TextView) view.findViewById(R.id.text_fragment2);
        if(getArguments().getString("f2_params") != null) {
            txtTitleView.setText(txtTitleView.getText()+ "params:" + getArguments().getString("f2_params"));
        }
        return view;
    }

    @Override
    public void onStart() {
        Log.i(F2_Fragment, "onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(F2_Fragment, "onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(F2_Fragment, "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(F2_Fragment, "onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i(F2_Fragment, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(F2_Fragment, "onDetach()");
        super.onDetach();
    }
}
