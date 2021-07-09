package com.example.mastermind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname ="Register.db";
    public DBHelper(@Nullable Context context) {
        super(context, "Register.db", null, 1);
    }

    private static final String TABLE_NAME = "words";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String Type = "type";
    private SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table usersdata(username TEXT, email TEXT,password TEXT)");
        db.execSQL("create Table words(id  INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists usersdata");
        db.execSQL("drop Table if exists words");
    }
    public void addWords(WordsModelClass wordsModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, wordsModelClass.getName());
        contentValues.put(DBHelper.Type, wordsModelClass.getType());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DBHelper.TABLE_NAME, null,contentValues);
    }
    public List<WordsModelClass> getWordsList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<WordsModelClass> storeWords = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String type = cursor.getString(2);
                storeWords.add(new WordsModelClass(id,name,type));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeWords;
    }

    public void updateWords(WordsModelClass wordsModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, wordsModelClass.getName());
        contentValues.put(DBHelper.Type, wordsModelClass.getType());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(wordsModelClass.getId())});
    }

    public void deleteWords(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
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
