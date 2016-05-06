package com.clark.demo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clark.sql.DataDbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ClarkWu on 2016/3/30.
 */
public class DataActivity extends Activity {
    private EditText shared_edit;
    private EditText file_edit;
    private EditText sql_edit;

    private Button shared_save_btn;
    private Button shared_read_btn;
    private Button file_save_btn;
    private Button file_read_btn;
    private Button sql_save_btn;
    private Button sql_read_btn;

    private static final String DATA_PREF_KEY = "data_key";

    private DataDbHelper db = new DataDbHelper(this);

    public void saveDataByInternalMethods(){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(DATA_PREF_KEY,Context.MODE_PRIVATE);
            if(file_edit.getText().toString().trim().length()<= 0){
                return;
            }
            fos.write(file_edit.getText().toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveDataByExternalmethods(){
        File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),DATA_PREF_KEY);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            if(file_edit.getText().toString().trim().length()<= 0){
                return;
            }
            fos.write(file_edit.getText().toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public long saveDataBySQLiteMethods(){
        if(sql_edit.getText().toString().trim().length()<= 0){
            return 0;
        }
        String data_text = sql_edit.getText().toString();
        SQLiteDatabase sqlDb = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("data_text", data_text);
        long itemId = sqlDb.update("data_db", values, null, null);
//        sqlDb.execSQL("update data_db set data_text = '" + data_text + "';");
        return 1;
    }

    public String readDataBySQLiteMethods(){
        SQLiteDatabase sqlDb = db.getReadableDatabase();
        Cursor cursor = sqlDb.query("data_db",null,null,null,null,null,null);
//        Cursor cursor = sqlDb.rawQuery("select * from data_db",null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("data_text"));
            return name;
        }
        return null;
    }

    public String readDataByInternalMethods(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(DATA_PREF_KEY);
            byte[] bytes = new byte[1024];
            StringBuffer sb = new StringBuffer();
            int b = -1;
            while((b = fis.read(bytes))!= -1){
                sb.append((new String(bytes,0,b)));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally{
            try {
                if(fis!= null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readDataByExternalmethods(){
        if(!isSDCardReadable()){
            return null;
        }


        File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),DATA_PREF_KEY);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[1024];
                StringBuffer sb = new StringBuffer();
               int b = -1;
                while((b = fis.read(bytes))!= -1){
                    sb.append((new String(bytes,0,b)));
                }
                return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(fis!= null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isSDCardWritable(){
        String state = Environment.MEDIA_MOUNTED;
        if(state.equals(Environment.getExternalStorageState())){
            return true;
        }
        return false;
    }

    public boolean isSDCardReadable(){
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return true;
        }else{
            return false;
        }
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.data_main);

        shared_edit = (EditText) findViewById(R.id.edit_prefLayout);
        shared_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() <= 0) {
                    shared_save_btn.setEnabled(false);

                } else {
                    shared_save_btn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        file_edit = (EditText) findViewById(R.id.edit_file_Layout);
        file_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() <= 0) {
                    file_save_btn.setEnabled(false);
                } else {
                    file_save_btn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sql_edit = (EditText)findViewById(R.id.edit_sql_Layout);
        sql_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() <= 0) {
                    sql_save_btn.setEnabled(false);
                } else {
                    sql_save_btn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        shared_save_btn = (Button)findViewById(R.id.edit_pref_savebtn);
        shared_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(DATA_PREF_KEY, Context.MODE_PRIVATE).edit();
                editor.putString(DATA_PREF_KEY, shared_edit.getText().toString());
                editor.apply();
                Toast.makeText(DataActivity.this, "存储成功！", Toast.LENGTH_LONG).show();
            }
        });

        shared_read_btn = (Button)findViewById(R.id.edit_pref_readbtn);
        shared_read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = getSharedPreferences(DATA_PREF_KEY, Context.MODE_PRIVATE).getString(DATA_PREF_KEY, "");
                Toast.makeText(DataActivity.this, "存储的值是:" + message, Toast.LENGTH_LONG).show();
            }
        });


        file_save_btn = (Button)findViewById(R.id.file_savebtn);
        file_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveDataByInternalMethods();//internal datas
                if(isSDCardWritable()) {
                    saveDataByExternalmethods();//external datas
                    Toast.makeText(DataActivity.this, "存储成功！", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DataActivity.this, "读取SD卡失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        file_read_btn = (Button)findViewById(R.id.file_readbtn);
        file_read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String sb = readDataByInternalMethods();read internal files
                String sb = readDataByExternalmethods();
                Toast.makeText(DataActivity.this,sb,Toast.LENGTH_LONG).show();
            }
        });

        sql_save_btn = (Button)findViewById(R.id.sql_savebtn);
        sql_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long itemId = saveDataBySQLiteMethods();
                if(itemId > 0){
                    Toast.makeText(DataActivity.this, "存储成功！", Toast.LENGTH_LONG).show();
                    sql_save_btn.setEnabled(true);
                }else{
                    Toast.makeText(DataActivity.this, "存储失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        sql_read_btn = (Button)findViewById(R.id.sql_readbtn);
        sql_read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = readDataBySQLiteMethods();
                if(data!= null){
                    Toast.makeText(DataActivity.this,data,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DataActivity.this,"读取失败",Toast.LENGTH_LONG).show();
                }
            }
        });

        if(getSharedPreferences(DATA_PREF_KEY,Context.MODE_PRIVATE).getString(DATA_PREF_KEY,null) == null){
            shared_read_btn.setEnabled(false);
        }else{
            shared_read_btn.setEnabled(true);
        }

        String sb = readDataByInternalMethods();
        if(sb != null) {
            if (sb.toString().trim().length() > 1) {
                file_read_btn.setEnabled(true);
            } else {
                file_read_btn.setEnabled(false);
            }
        }

        String db_sb = readDataBySQLiteMethods();
        if(db_sb!= null && db_sb.trim().length() > 1){
            sql_read_btn.setEnabled(true);
        }else{
            sql_read_btn.setEnabled(false);
        }

        shared_save_btn.setEnabled(false);
        file_save_btn.setEnabled(false);
        sql_save_btn.setEnabled(false);

    }
}
