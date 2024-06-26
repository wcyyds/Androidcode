package com.example.sqlitetest_03211093;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private SQLiteDatabase db;
    public DB (Context context){
        super(context,"TEXT1.db",null,1);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table users(name text primary key,psw text not null,phone text not null)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean add(String name,String psw,String phone){
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("psw",psw);
        values.put("phone",phone);
        long i=db.insert("users",null,values);
        if (i>0){
            Log.d("","插入成功");
            return true;
        }
        return false;
    }

    public boolean del(String name){
        long i=db.delete("users","name=?",new String[]{name});
        if(i>0){
            Log.d("","删除成功");
            return true;
        }
        return false;
    }

    public Boolean change(String name,String NewPsw,String Newphone){
        ContentValues values=new ContentValues();
        values.put("psw",NewPsw);
        values.put("phone",Newphone);
        long i=db.update("users",values,"name=?",new String[]{name});
        if(i>0) {
            Log.d("", "更新成功");
            return true;
        }
        return  false;
    }
    public ArrayList getall() {
        ArrayList array = new ArrayList();
        Cursor cursor = db.query("users", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String psw = cursor.getString(cursor.getColumnIndex("psw"));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
            User u = new User(name, psw,phone);
            array.add(u);
        }
        return array;
    }
}
