package com.clark.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.clark.adapter.GLViewAdapter;
import com.clark.gldemo.FirstGLActivity;
import com.clark.inter.OnRecyclerViewClickListener;

/**
 * Created by Administrator on 2016/5/7.
 */
public class OpenGLActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gllist);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.glRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);

                for (int i = 0; i < parent.getChildCount(); i++) {
                    int left = parent.getLeft();
                    int right = parent.getWidth();
                    int height = i * parent.getChildAt(i).getHeight();
                    canvas.drawLine(left, height, right, height, paint);
                }
            }
        });

        final GLViewAdapter glViewAdapter = new GLViewAdapter();
        glViewAdapter.setOnRecyclerItemListener(new OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent();
                        intent.setClass(OpenGLActivity.this, FirstGLActivity.class);
                        startActivity(intent);
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                }
            }
        });

        recyclerView.setAdapter(glViewAdapter);
    }
}
