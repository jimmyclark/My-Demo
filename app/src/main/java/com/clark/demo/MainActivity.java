package com.clark.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.clark.adapter.MainViewAdapter;
import com.clark.inter.OnRecyclerViewClickListener;

public class MainActivity extends Activity {
    private static final String MAIN_LOG = "MainActivity";

    private static final int MAIN_COLS = 2;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MAIN_LOG,"onCreate");
//        if(savedInstanceState != null){
//            Log.i(MAIN_LOG,"onCreate nima" + savedInstanceState.getInt("nima"));
//        }
        super.onCreate(savedInstanceState);
        if(getIntent()!= null){
            Uri uri = getIntent().getData();
            if(uri != null){
                String message = uri.getQueryParameter("message");
                Toast.makeText(this,"浏览器传入的数据:" + message,Toast.LENGTH_LONG).show();
            }
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_main);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(MAIN_COLS,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration(){
            @Override
            public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                Paint paint = new Paint();
                paint.setColor(Color.RED);

                for(int i = 0; i<parent.getChildCount();i++){
                    if(i % 2 == 0){
                        int left = parent.getChildAt(i).getLeft() + 10;
                        int top = parent.getChildAt(i).getTop();
                        int width = parent.getChildAt(i).getWidth();
                        int height = parent.getChildAt(i).getHeight();
                        canvas.drawLine(left + width,top, left+width,top+height, paint);
                    }
                }

            }
        });
        MainViewAdapter mainAdapter = new MainViewAdapter();
        mainAdapter.setOnRecyclerItemListener(new OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position == 0){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,TutorialActivity.class);
                    startActivity(intent);
                }else if(position == 1){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,WorkActivity.class);
                    startActivity(intent);
                }else if(position == 2){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,OpenGLActivity.class);
                    startActivity(intent);
                }
            }
        });

        recyclerView.setAdapter(mainAdapter);
//        ImageView imageView = (ImageView) findViewById(R.id.imgview);
//        Bitmap bitmap = createUrlQRImg("http://www.baidu.com");
//        if(bitmap != null){
//            Toast.makeText(this,"bitmap is not null",Toast.LENGTH_LONG ).show();
//        }else{
//            Toast.makeText(this,"bitmap is null", Toast.LENGTH_LONG).show();
//        }
//        imageView.setImageBitmap(bitmap);
    }
}
