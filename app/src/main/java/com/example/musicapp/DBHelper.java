package com.example.musicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="musicapp.db";

    public DBHelper( Context context) {
        super(context, "musicapp.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key,password TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }
    public Boolean insertData(String username,String password,String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("email",email);
        long result=db.insert("users",null,contentValues);

        if (result==-1) return false;
        else return true;

    }
    public Boolean checkusername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where Username = ?",new String[] {username});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public Boolean checkemail(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where Email = ?",new String[] {email});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where Username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
