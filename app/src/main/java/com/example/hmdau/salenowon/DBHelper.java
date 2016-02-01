package com.example.hmdau.salenowon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hmdau on 2016/01/26.
 */


public class DBHelper extends SQLiteOpenHelper {

    public static final String table_name = "Users";
    public static final String name = "name";
    public static  String email = "email";
    public static  String password = "password";

    private static final String DATABASE_NAME = "users.sqlite";// "users.db";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        db.execSQL("CREATE TABLE IF NOT EXISTS " + table_name + "(name VARCHAR(50) NOT NULL, email VARCHAR(100) UNIQUE, password text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String namev, String emailv, String passwordv) {

        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
        ContentValues contentValues = new ContentValues();
        contentValues.put(name, namev);
        contentValues.put(email, emailv);
        contentValues.put(password, passwordv);


        return db.insert(table_name, null, contentValues);
    }

    public void getAllEmails()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select email from users", null);
        res.moveToFirst();

        while (!res.isAfterLast())
        {
            System.out.println(res.getString(0));
            res.moveToNext();
        }

       res.close();
    }
    

    public Cursor getData(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select name from users where email='" + email + "' AND password='"+password+"'", null);
        res.moveToFirst();

        return res;


    }



}
