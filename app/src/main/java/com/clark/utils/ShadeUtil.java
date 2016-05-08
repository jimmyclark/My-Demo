package com.clark.utils;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 加载顶点和片元着色器的类
 */
public class ShadeUtil {
    private static final String GLES_ERROR = "ES_20_ERROR";

    /**
     * 加载着色器方法
     * @param shaderType 着色器类型
     * @param source 着色器的脚本字符串
     * @return 着色器id -- 0 错误   其他 成功
     */
    public static int loadShader(int shaderType,String source){
        //创建着色器
        int shader = GLES20.glCreateShader(shaderType);
        //创建成功
        if(shader != 0){
            GLES20.glShaderSource(shader,source);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            //编译着色器
            GLES20.glGetShaderiv(shader,GLES20.GL_COMPILE_STATUS,compiled,0);
            //编译失败
            if(compiled[0] == 0){
                Log.e(GLES_ERROR,"Could not compile shader " + shaderType + ";");
                Log.e(GLES_ERROR,GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    /**
     * 创建着色器程序的方法
     * @param vertexSource 顶点着色器资源
     * @param frgmentSource 片元着色器资源
     * @return 着色器程序结果 0-失败  其他-成功
     */
    public static int createProgram(String vertexSource,String frgmentSource){
        //加载顶点着色器
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,vertexSource);
        if(vertexShader == 0){
            return 0;
        }

        //加载片元着色器
        int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER,frgmentSource);
        if(pixelShader == 0){
            return 0;
        }

        //GPU 加载完毕
        //创建着色器程序
        int program = GLES20.glCreateProgram();
        //程序创建成功，则向程序中加入顶点着色器和片元着色器
        if(program != 0){
            //加入顶点着色器
            GLES20.glAttachShader(program,vertexShader);
            checkGLError("glAttachShader");
            //加入片元着色器
            GLES20.glAttachShader(program,pixelShader);
            checkGLError("glAtaachShader");
            //链接程序
            GLES20.glLinkProgram(program);
            int [] linkStatus = new int [1];
            GLES20.glGetProgramiv(program,GLES20.GL_LINK_STATUS,linkStatus,0);
            //链接失败，删除程序
            if(linkStatus[0] != GLES20.GL_TRUE){
                Log.e(GLES_ERROR,"Could not link program:");
                Log.e(GLES_ERROR,GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }

    /**
     * 检查每一步操作是否有错误方法
     * @param op 错误原因
     */
    public static void checkGLError(String op){
        int error;
        while((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR){
            Log.e(GLES_ERROR,op+": glError:" + error);
            throw new RuntimeException(op+": glError:" + error);
        }
    }

    /**
     * 从sh脚本加载着色器内容的方法
     * @param fname assets下文件的名字
     * @param r 资源
     * @return 着色器内容文本
     */
    public static String loadFromAssetsFile(String fname,Resources r){
        String result = null;
        try{
            InputStream in = r.getAssets().open(fname);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((ch = in.read()) != -1){
                baos.write(ch);
            }
            byte[] buff = baos.toByteArray();
            baos.close();
            in.close();
            result = new String(buff,"UTF-8");
            result = result.replaceAll("\\r\\n","\n");
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
