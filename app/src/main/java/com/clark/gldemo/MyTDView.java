package com.clark.gldemo;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.clark.matrix.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2016/5/7.
 */
public class MyTDView extends GLSurfaceView {
    //每次三角形旋转的角度
    final float ANGLE_SPAN = 0.375f;
    //自定义线程类RotateThread的引用
    RotateThread rotateThread;
    //自定义渲染器的引用
    SceneRenderer mRenderer;

    private class SceneRenderer implements  GLSurfaceView.Renderer{
        Triangle tie;//声明Triangle的引用

        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            GLES20.glClearColor(0,0,0,1.0f);//设置屏幕背景色
            tie = new Triangle(MyTDView.this);//创建Triangle类的对象
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            rotateThread = new RotateThread();//创建RotateThread对象
            rotateThread.start();//开启线程
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int width, int height) {
            GLES20.glViewport(0,0,width,height);//设置视口
            float ratio = (float) width/height;//计算屏幕的宽度和高度比例
            Matrix.frustumM(Triangle.mProjMatrix,0,-ratio,ratio,-1,1,1,10);//设置透视投影
            Matrix.setLookAtM(Triangle.mVMatrix,0,0,0,3,0f,0f,0f,0f,1.0f,0.0f);//设置摄像机
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT| GLES20.GL_COLOR_BUFFER_BIT);
            tie.drawSelf();//通过Triangle的对象调用drawSelf绘制三角形
        }
    }

    public MyTDView(Context context) {
        super(context);
        this.setEGLContextClientVersion(2);//使用open GL ES2.0需要该设置该值为2
        mRenderer = new SceneRenderer();//创建SceneRenderer类的对象
        this.setRenderer(mRenderer);//设置渲染器
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    /**
     * 自定义内部类线程
     */
    public class RotateThread extends Thread{
        public boolean flag = true;//设置循环标志位
        public void run(){
            while(flag){
                mRenderer.tie.xAngle = mRenderer.tie.xAngle + ANGLE_SPAN;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

