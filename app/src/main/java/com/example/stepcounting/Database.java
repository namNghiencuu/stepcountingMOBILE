package com.example.stepcounting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {
    //Tên DB, tên Table, Tên Column
    public static final String DATABASE_NAME = "counting.db";
    public static final String TABLE_NAME = "counting_table";
    public static final String COl_1 = "ID";
    public static final String COl_2 ="Step";
    public Database( Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng
        db.execSQL(" CREATE TABLE "+TABLE_NAME +" ("+COl_1+" INTEGER primary key, "+COl_2+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop Table If Exists
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public Cursor getAllData(){
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean insertData(String NumStep){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl_2 , NumStep);
        long results = db.insert(TABLE_NAME,null, contentValues);
        if (results == -1)
            return false;
        else
            return true;
    };
}
