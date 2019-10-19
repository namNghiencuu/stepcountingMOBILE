package com.example.stepcounting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    //Tên DB, tên Table, Tên Column
    public static final String DATABASE_NAME = "counting.db";
    public static final String TABLE_NAME = "counting_table";
    public static final Integer COl_1 = Integer.valueOf("ID");
    public static final Integer COl_2 = Integer.valueOf("Step");
    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng
        db.execSQL("create table" + TABLE_NAME + " ("+COl_1+" integer primary key, "+COl_2+" integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop Table If Exists
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
