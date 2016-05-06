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

import com.clark.adapter.WorkViewAdapter;
import com.clark.inter.OnRecyclerViewClickListener;

/**
 * Created by ClarkWu on 2016/3/23.
 */
public class WorkActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.worklist);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.workRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                Paint paint = new Paint();
                paint.setColor(Color.RED);

                for (int i = 0; i < parent.getChildCount(); i++) {
                    int left = parent.getLeft();
                    int right = parent.getWidth();
                    int height = i*parent.getChildAt(i).getHeight();
                    canvas.drawLine(left, height, right, height, paint);
                }
            }
        });

        final WorkViewAdapter workViewAdapter = new WorkViewAdapter();
        workViewAdapter.setOnRecyclerItemListener(new OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(int position) {
                switch(position){
                    case 0:
                        Intent intent = new Intent(WorkActivity.this,QRCodeActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent contactActivityIntent = new Intent(WorkActivity.this,ContactActivity.class);
                        startActivity(contactActivityIntent);
                        break;
                    case 2:
                        Intent jumpActivityIntent = new Intent(WorkActivity.this,JumpAppActivity.class);
                        startActivity(jumpActivityIntent);
                        break;
                }
            }
        });

        recyclerView.setAdapter(workViewAdapter);
    }
}
