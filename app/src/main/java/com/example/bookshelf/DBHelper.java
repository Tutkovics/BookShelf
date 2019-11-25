package com.example.bookshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookshelf.data.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    public static final String BOOKS_COLUMN_AUTHOR = "author";
    public static final String BOOKS_COLUMN_REVIEW = "review";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (id integer primary key, tag text,name text,registration text)");
        db.execSQL("create table books (id integer primary key, title text, author text, description text,readed text,userId text, review text)");
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
        db.insert(USERS_TABLE_NAME, null, contentValues);
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
        String queryString = "select * from " + USERS_TABLE_NAME + " where tag='" + tag + "'";
        Cursor res =  db.rawQuery(  queryString, null );
        return res;
    }

    public List<Book> getUserBooks(String tag) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "select * from " + BOOKS_TABLE_NAME + " where " + BOOKS_COLUMN_USERID + "='" + tag + "'";
        Cursor res =  db.rawQuery(  queryString, null );

        List<Book> userBookList = new ArrayList<Book>();

        if(res != null && res.getCount()>0){
            for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()){
                Book currentBook = new Book();
                currentBook.setTitle( res.getString(res.getColumnIndex(BOOKS_COLUMN_TITLE)));
                currentBook.setAuthor( res.getString(res.getColumnIndex(BOOKS_COLUMN_AUTHOR)));
                currentBook.setReaded(res.getString(res.getColumnIndex(BOOKS_COLUMN_READED)));
                currentBook.setDescription(res.getString(res.getColumnIndex(BOOKS_COLUMN_DESCRIPTION)));
                currentBook.setReview(res.getString(res.getColumnIndex(BOOKS_COLUMN_REVIEW)));


                userBookList.add(currentBook);
            }
        }
        return userBookList;
    }
}
