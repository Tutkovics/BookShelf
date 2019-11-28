package com.example.bookshelf.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Book.class},
        version = 1
)
public abstract class BookListDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}
