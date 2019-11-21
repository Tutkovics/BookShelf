package com.example.bookshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BookShelf.db";
    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_TAG = "tag";
    public static final String USERS_COLUMN_NAME = "name";
    public static final String USERS_COLUMN_REGISTRATION = "registration";

    public static final String BOOKS_TABLE_NAME = "books";
    public static final String BOOKS_COLUMN_ID = "id";
    public static final String BOOKS_COLUMN_TITLE = "title";
    public static final String BOOKS_COLUMN_DESCRIPTION = "description";
    public static final String BOOKS_COLUMN_READED = "readed";
    public static final String BOOKS_COLUMN_USERID = "userId";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (id integer primary key, tag text,name text,registration text)");
        db.execSQL("create table books (id integer primary key, title text,description text,readed text,userId text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    public boolean insertUser (String tag, String name, String registration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("tag", tag);
        contentValues.put("registration", registration);
        db.insert("users", null, contentValues);
        return true;
    }

    public boolean updateUser (Integer id, String tag, String name, String registration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("tag", tag);
        contentValues.put("registration", registration);
        db.update("users", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteUser (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getUserData(String tag) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users where tag="+tag+"", null );
        return res;
    }
}
