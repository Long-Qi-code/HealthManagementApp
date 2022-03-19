package com.example.healthmanagementapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserService {
    private DatabaseHelper dbHelper;
    private String id;

    public UserService(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public boolean login(String username,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    public boolean register(User user){
        SQLiteDatabase sdb=dbHelper.getWritableDatabase();
        String sql="insert into user(username,password,age,sex,phone) values(?,?,?,?,?)";
        Object obj[]={user.getUsername(),user.getPassword(),user.getAge(),user.getSex(),user.getPhone()};
        sdb.execSQL(sql, obj);
        return true;
    }

    //判断数据库中是否已经有该用户名
    public boolean isUser(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from user where username=?";
        Cursor cursor = db.rawQuery(sql, new String[] {name});
        if (cursor.getCount()!=0) {
            cursor.close();
            return true;
        }
        return false;
    }

    public String getUserId(String name,String password){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql="select id from user where username=? and password=?";
        /**
         *  rawQuery()方法的第一个参数为select语句；第二  个参数为select语句中占位符参数的值，
         *如果select语句没有使用占位符，该参数可以设置为null。
         *  查询中，得到的cursor的初始位置是指向第一条记录的前一个位置的
         */
        Cursor cursor = db.rawQuery(sql, new String[] {name, password});
        //moveToNext将cursor移动到第一条记录上
        while(cursor.moveToNext()){
            id = Integer.toString(cursor.getInt(0));
            System.out.println(id);

        }
        cursor.close();
        return id;
    }


}
