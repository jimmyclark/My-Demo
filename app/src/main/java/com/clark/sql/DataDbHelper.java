package com.clark.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ClarkWu on 2016/3/31.
 */
public class DataDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "data_demo.db";
    private static final String TEXT_TYPE = "TEXT";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE data_db" + " (id"  + " INTEGER PRIMARY KEY," +
           "data_text " +  TEXT_TYPE + ");" ;
    private static final String SQL_DROP_ENTRIES = "DROP TABLE IF EXISTS datas" ;

    public DataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.insert("data_db","data_text",null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_ENTRIES);
        onCreate(db);

    }
}
