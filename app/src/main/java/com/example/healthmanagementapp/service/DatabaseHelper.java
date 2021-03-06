package com.example.healthmanagementapp.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static  String name="user.db";
    static int dbVersion=1;
    public DatabaseHelper(Context context){
        super(context, name, null, dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(id integer primary key autoincrement, " +
                "username varchat(20)," +
                "password varchar(20)," +
                "age integer," +
                "sex varchar(2)," +
                "phone varchar(20)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
