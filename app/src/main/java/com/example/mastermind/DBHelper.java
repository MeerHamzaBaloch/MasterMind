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
        db.execSQL("create Table usersdata(username TEXT, email TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists usersdata");
    }

    public Boolean insertData(String username,String email,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert( "usersdata", null, contentValues);
        if (result==-1) return false;
        else return true;
    }

    public  boolean checkusersemail(String email, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "Select * from usersdata where email = ? OR username =?", new String[] {email , username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean  checkusersemailpassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usersdata where username =? and password =?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
