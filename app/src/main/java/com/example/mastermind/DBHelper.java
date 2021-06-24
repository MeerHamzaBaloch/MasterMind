package com.example.mastermind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname ="Register.db";
    public DBHelper(@Nullable Context context) {
        super(context, "Register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table usersdata(username TEXT primary key, email TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists usersdata");
    }

    public Boolean insertData(String email,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert( "usersdata", null, contentValues);
        if (result==-1) return false;
        else return true;
    }

    public  boolean checkusersemail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "Select * from usersdata where email = ?", new String[] {email});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean  checkusersemailpassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usersdata where email =? and password =?", new String[] {email,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
